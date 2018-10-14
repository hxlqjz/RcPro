
/**
 *
 * 说明：
 * 1.导出方法的形式
 *
 *  exportExcelRecord(grid,meta)  //grid：要导出的grid必须参数；meta为自定义属性,为JSon对象，行如：{op:"page",width:"A:C=20"}
 *
 * 导出形式
 * (1) 本页导出：默认导出方法
 * (2) 选择导出：选择记录导出
 * (3) 全部导出：导出所有的记录
 * (4) 后台导出：直接在后台导出  优点：速度快,适合大批量导出。缺点：导出数据本身内容，没有像ext的renderer函数处理
 * 形式 op:back,exportUrl:test/test.action。
 *
 * exportUrl 在相应的action添加导出方法，形式和查询方法一样
 * List dataList = pageObject.getList();
 * ExcelUtil.exportExcelBack(dataList, request,response);
 *
 *
 *2.有的列，如合计列，没有dataIndex,可以指定任意的没有用过的值 如:'hj',即可。
 */

//excel配置属性
var excelCf = {
	op : "", //导出方式,默认为本页面导出,simple或空:本页导出， page:分页导出,select:选择指定行导出,back:后台带出,model: 模板导出
	rownum : false, //是否显示序号
	title : "",//显示表名
	width : "",//列号=宽度，多个用逗号隔开  如 "A:C=25,E=15";下标从A开始，页面上第一个字段列为A
	height : "",//行号=高度，多个用逗号隔开  如 "1:4=10,10=25"；下标从1开始，第一行数据
	format : "", //单元格格式，文本：@, 日期：yyyy-mm-dd, 数字：###.## ，格式：列下标号=format,多个用逗号隔开, "1=@,11=yyyy-mm-dd"；下标从0开始，页面上第一个字段列为0
	fileName : "", //文件名
	sheetName : "", //sheet名
	ieExport : true, //是否IE导出
	rowColor : "", //行样式 颜色:行下标号=color,如 0=red,1=green
	colColor : "",//列样式 颜色:列下标号=color,如0=blue,1=yellow
	isSheet : false, //是否分sheet导出
	sheetSize : 5000, //每个sheet的大小
	exportUrl : "", //后台导出时用，导出URL
	headTable : "", //导出时表格标题前面加特殊数据
	isBorder : false
};

//初始化配置属性
function initExcelCf(meta) {
	if (!meta) {
		meta = {};
	}
	for ( var tmp in meta) {
		excelCf[tmp] = meta[tmp];
	}
}

//导出Excel
function exportExcelRecord(grid, meta) {
	var ds = grid.getStore();
	if (ds == null || ds.getTotalCount() == 0) {
		Ext.Msg.alert('提示信息', '无数据进行导出！');
		return;
	}
	Ext.Msg.confirm('提示信息', '确认要导出数据吗？', function(id) {
		if (id == "yes") {

			//初始化配置属性
			initExcelCf(meta);

			//分页
			if (excelCf.op == "page") {
				var url = ds.proxy.conn.url;
				var fields = ds.fields.items;
				var baseParams = ds.baseParams;
				var params = [];
				for ( var x in baseParams) {
					//过滤空值,undefind值
					if (isEmpty(baseParams[x])) {
						baseParams[x] = "";
					}
					params[x] = baseParams[x];
				}

				var Ds = new Ext.data.JsonStore({
					url : url,
					root : 'list',
					totalProperty : 'totalCount',
					fields : fields
				});

				Ds.on('beforeload', function() {
					this.baseParams = params;
				});

				Ds.load({
					params : {
						start : 0,
						limit : 0
					}
				});

				var Grid = new Ext.grid.GridPanel({
					ds : Ds,
					cm : grid.getColumnModel()
				});

				Ds.on("load", function() {
					exportExcel(Grid);
				});

			} else if (excelCf.op == "back") {
				exportExcelBack(grid);
			} else if (excelCf.op == "model") {
				exportExcelModel(grid);
			} else {
				exportExcel(grid);
			}

		}
	});
}

function exportExcelModel(grid) {
	var baseParams = grid.getStore().baseParams;
	var params = [];
	for ( var x in baseParams) {
		if (isEmpty(baseParams[x])) {
			baseParams[x] = "";
		}
		params.push("<input type='hidden' name='" + x + "' value='"
				+ baseParams[x] + "' />");
	}
	document.getElementById("excelForm").setAttribute("action",
			excelCf.exportUrl);
	document.getElementById("excelFormParams").innerHTML = params.join("");
	document.getElementById("excelForm").submit();
	document.getElementById("excelFormParams").innerHTML = "";
}

function exportExcel(grid) {
	var ds = grid.getStore(); //ds
	var cm = grid.getColumnModel(); //cm
	var cmLen = cm.getColumnCount(); //cm长度
	var formatArr = []; //格式属性
	var renderArr = []; //render函数
	var vLen = 0; //列下标有效长度
	var vIndex = []; //列下标是否有效
	var multiTh = getMultiCm(cm, cmLen); //多表头

	for ( var i = 0; i < cmLen; i++) {
		if (cm.isHidden(i) || isEmpty(cm.getDataIndex(i))) {
			vIndex[i] = false;
			renderArr[i] = "";
		} else {
			vLen++;
			vIndex[i] = true;
			renderArr[i] = cm.getRenderer(i);
			if (renderArr[i] == Ext.grid.ColumnModel.defaultRenderer) {
				renderArr[i] = "";
			}
			formatArr[formatArr.length] = "";
		}
	}

	if (excelCf.format != "") {
		var format = excelCf.format.split(",");
		var tmpArr = [];
		for ( var j = 0; j < format.length; j++) {
			tmpArr = format[j].split("=");
			formatArr[tmpArr[0]] = tmpArr[1];
		}
	}

	if (excelCf.ieExport) {
		//IE导出
		exportIE(ds, cm, cmLen, formatArr, renderArr, vLen, vIndex, multiTh,
				grid);
	} else {
		//后台导出
		exportBack(ds, cm, cmLen, formatArr, renderArr, vLen, vIndex, multiTh,
				grid);
	}
}

//自定义后台导出
function exportExcelBack(grid) {
	var cm = grid.getColumnModel();
	var cmLen = cm.getColumnCount();

	var colName = [];
	var dataIndex = [];
	for ( var i = 0; i < cmLen; i++) {
		if (!cm.isHidden(i) && cm.getDataIndex(i)) {
			colName[colName.length] = cm.getColumnHeader(i);
			dataIndex[dataIndex.length] = cm.getDataIndex(i);
		}
	}

	var baseParams = grid.getStore().baseParams;
	var params = [];
	for ( var x in baseParams) {
		if (isEmpty(baseParams[x])) {
			baseParams[x] = "";
		}
		params.push("<input type='hidden' name='" + x + "' value='"
				+ baseParams[x] + "' />");
	}

	document.getElementById("excelForm").setAttribute("action",
			excelCf.exportUrl);
	document.getElementById("excelFormParams").innerHTML = params.join("");
	document.getElementById("excelFormColName").value = colName.join(",");
	document.getElementById("excelFormDataIndex").value = dataIndex.join(",");
	document.getElementById("excelFormTitle").value = excelCf.title;
	document.getElementById("excelFormFileName").value = excelCf.fileName;
	document.getElementById("excelFormSheetName").value = excelCf.sheetName;
	document.getElementById("excelFormWidths").value = excelCf.width;
	document.getElementById("excelFormRowColor").value = excelCf.rowColor;
	document.getElementById("excelFormColColor").value = excelCf.colColor;
	document.getElementById("excelFormIsSheet").value = excelCf.isSheet;
	document.getElementById("excelFormSheetSize").value = excelCf.sheetSize;
	document.getElementById("excelFormFormat").value = excelCf.format;
	document.getElementById("excelForm").submit();
	document.getElementById("excelFormParams").innerHTML = "";
	document.getElementById("excelFormColName").value = "";
	document.getElementById("excelFormDataIndex").value = "";
	document.getElementById("excelFormTitle").value = "";
	document.getElementById("excelFormFileName").value = "";
	document.getElementById("excelFormSheetName").value = "";
	document.getElementById("excelFormWidths").value = "";
	document.getElementById("excelFormRowColor").value = "";
	document.getElementById("excelFormColColor").value = "";
	document.getElementById("excelFormIsSheet").value = "";
	document.getElementById("excelFormSheetSize").value = "";
	document.getElementById("excelFormFormat").value = "";
}

function exportBack(ds, cm, cmLen, formatArr, renderArr, vLen, vIndex, multiTh,
		grid) {
	var thead = []; // th
	var tbody = []; // td
	var tbodyC = []; //
	var record = null;
	var tmpVal = "";

	//thead
	if (multiTh == "") {
		thead[thead.length] = "<tr>";
		for ( var i0 = 0; i0 < cmLen; i0++) {
			if (vIndex[i0]) {
				thead[thead.length] = "<th>" + cm.getColumnHeader(i0) + "</th>";
			}
		}
		thead[thead.length] = "</tr>";
	} else {
		thead[0] = multiTh;
	}
	thead = thead.join("");

	// tbody
	var len = ds.getCount();
	var selRecords = null;
	if (excelCf.op == "select") {
		selRecords = grid.getSelectionModel().getSelections();
		len = selRecords.length;
	}
	for ( var i = 0; i < len; i++) {
		tbodyC = [];
		if (selRecords == null) {
			record = ds.getAt(i);
		} else {
			record = selRecords[i];
		}
		if (excelCf.rownum) {
			tbodyC[tbodyC.length] = i + 1;
		}
		for ( var j = 0; j < cmLen; j++) {
			if (vIndex[j]) {
				tmpVal = record.get(cm.getDataIndex(j));
				if (renderArr[j] != "") {
					tmpVal = renderArr[j](tmpVal, {
						css : ""
					}, record, i, j, ds);
				}
				tmpVal = tmpVal == null ? "" : tmpVal;
				tbodyC[tbodyC.length] = tmpVal;
			}
		}
		tbody[tbody.length] = tbodyC;
	}
	tbody = Ext.encode(tbody);
	exportBackHtml(thead, tbody);
}

//后台导出
function exportBackHtml(thead, tbody) {
	document.getElementById("excelForm").setAttribute("action",
			"excel/exportExcel.action");
	document.getElementById("excelFormThead").value = thead;
	document.getElementById("excelFormTbody").value = tbody;
	document.getElementById("excelFormTitle").value = excelCf.title;
	document.getElementById("excelFormFileName").value = excelCf.fileName;
	document.getElementById("excelFormSheetName").value = excelCf.sheetName;
	document.getElementById("excelFormWidths").value = excelCf.width;
	document.getElementById("excelFormRowColor").value = excelCf.rowColor;
	document.getElementById("excelFormColColor").value = excelCf.colColor;
	document.getElementById("excelFormIsSheet").value = excelCf.isSheet;
	document.getElementById("excelFormSheetSize").value = excelCf.sheetSize;
	document.getElementById("excelFormFormat").value = excelCf.format;
	document.getElementById("excelForm").submit();
	document.getElementById("excelFormThead").value = "";
	document.getElementById("excelFormTbody").value = "";
	document.getElementById("excelFormTitle").value = "";
	document.getElementById("excelFormFileName").value = "";
	document.getElementById("excelFormSheetName").value = "";
	document.getElementById("excelFormWidths").value = "";
	document.getElementById("excelFormRowColor").value = "";
	document.getElementById("excelFormColColor").value = "";
	document.getElementById("excelFormIsSheet").value = "";
	document.getElementById("excelFormSheetSize").value = "";
	document.getElementById("excelFormFormat").value = "";
}

function exportIE(ds, cm, cmLen, formatArr, renderArr, vLen, vIndex, multiTh,
		grid) {
	var thead = [];//th
	if (excelCf.isBorder) {
		thead = [ "<table border=1><thead>" ];
	} else {
		thead = [ "<table><thead>" ];
	}
	var tbody = []; // td
	var html = [];
	var record = null;
	var tmpVal = "";
	var title = "";

	if (excelCf.title != "") {
		title = "<tr><th colspan="
				+ vLen
				+ " style='text-align: center;font-size:2em;font-weight:bold;'>"
				+ excelCf.title + "</th></tr>";
	}

	if (excelCf.headTable != "") {
		title = title + excelCf.headTable;
	}

	//thead
	if (multiTh == "") {
		thead[thead.length] = title + "<tr>";
		for ( var k = 0; k < cmLen; k++) {
			if (vIndex[k]) {
				thead[thead.length] = "<th>" + cm.getColumnHeader(k) + "</th>";
			}
		}
		thead[thead.length] = "</tr>";
	} else {
		thead[thead.length] = title + multiTh;
	}
	html[html.length] = thead.join("") + "</thead><tbody>";

	// tbody
	var len = ds.getCount();
	var selRecords = null;
	if (excelCf.op == "select") {
		selRecords = grid.getSelectionModel().getSelections();
		len = selRecords.length;
	}
	for ( var i = 0; i < len; i++) {
		tbody = [ "<tr>" ];
		if (selRecords == null) {
			record = ds.getAt(i);
		} else {
			record = selRecords[i];
		}
		if (excelCf.rownum) {
			tbody[tbody.length] = "<td>" + (i + 1) + "</td>";
		}
		var v = 0;
		for ( var j = 0; j < cmLen; j++) {
			if (vIndex[j]) {
				tmpVal = record.get(cm.getDataIndex(j));
				if (renderArr[j] != "") {
					tmpVal = renderArr[j](tmpVal, {
						css : ""
					}, record, i, j, ds);
				}
				tmpVal = tmpVal == null ? "" : tmpVal;
				if (formatArr[v] != "") {
					tbody[tbody.length] = "<td style='vnd.ms-excel.numberformat:"
							+ formatArr[v] + ";'>" + tmpVal + "</td>";
				} else {
					tbody[tbody.length] = "<td>" + tmpVal + "</td>";
				}
				v++;
			}
		}
		tbody[tbody.length] = "</tr>";
		html[html.length] = tbody.join("");
	}

	html[html.length] = "</tbody></table>";
	html = html.join("");
	exportIEHtml(html);
}

//IE导出
function exportIEHtml(tableHTML) {
	window.clipboardData.setData("Text", tableHTML);
	var width = [];
	var height = [];
	if (excelCf.width != "") {
		width = excelCf.width.split(",");
	}
	if (excelCf.height != "") {
		height = excelCf.height.split(",");
	}
	try {
		var ExApp = new ActiveXObject("Excel.Application"); 
		var ExWBk = ExApp.workbooks.add(); 
		var xlsheet = ExWBk.worksheets(1); 
		
		var tmpArr = [];
		for ( var i0 = 0; i0 < width.length; i0++) {
			tmpArr = width[i0].split("=");
			xlsheet.Columns(tmpArr[0]).ColumnWidth = tmpArr[1];
		}
		// 设置行高
		for ( var i = 0; i < height.length; i++) {
			tmpArr = height[i].split("=");
			xlsheet.Rows(tmpArr[0]).RowHeight = tmpArr[1];
		}
		ExApp.DisplayAlerts = false;
		ExApp.visible = true;
	} catch (e) {
		var msg = '请设置IE的菜单<br/>工具 -> Internet选项 -> 安全 -> 自定义级别 -> 对未标记为可安全执行脚本ActiveX控件初始化并执行脚本 -> 选择[启用]<br/>就可以生成Excel';
		Ext.Msg.alert('提示', msg);
		return false;
	}
	ExWBk.worksheets(1).Paste;
}

//获取下标间子数组和
function getSumByArr(arr, start, end) {
	var sum = 0;
	for ( var i = start; i < end; i++) {
		sum += arr[i];
	}
	return sum;
}

function renderNum(obj) {
	if (obj && !isNaN(obj)) {
		return obj;
	}
	return 1;
}

//获取多表头
function getMultiCm(cm, cmLen) {
	var thStr = "";
	var rowArr = cm.rows;
	if (!isEmpty(rowArr)) {
		var rowspanArr = [];
		var rowTmp = "";
		var tmpHeader = "";
		var tmpIndex = -1;
		var tmpRowspan = 1;
		var tmpColspan = 1;
		var hiddenArr = [];
		for ( var i0 = 0; i0 < cmLen; i0++) {
			rowspanArr[i0] = 1;
			if (cm.isHidden(i0) || isEmpty(cm.getDataIndex(i0))) {
				hiddenArr[i0] = 1;
			} else {
				hiddenArr[i0] = 0;
			}
		}

		for ( var i1 = 0, len = rowArr.length; i1 < len; i1++) {
			tmpIndex = -1;
			for ( var j0 = 0, len2 = rowArr[i1].length; j0 < len2; j0++) {
				rowTmp = rowArr[i1][j0];
				tmpHeader = rowTmp.header;
				tmpIndex += rowTmp.colspan;
				if (!tmpHeader) {
					rowspanArr[tmpIndex] += renderNum(rowTmp.rowspan);
				}
			}
		}

		for ( var i = 0; i < rowArr.length; i++) {
			thStr += "<tr>";
			tmpIndex = -1;
			for ( var j = 0; j < rowArr[i].length; j++) {
				rowTmp = rowArr[i][j];
				tmpHeader = rowTmp.header;
				tmpIndex += rowTmp.colspan;
				tmpRowspan = renderNum(rowTmp.rowspan);
				tmpColspan = rowTmp.colspan
						- getSumByArr(hiddenArr, tmpIndex - rowTmp.colspan + 1,
								tmpIndex);
				if (!tmpHeader) {
					if (rowspanArr[tmpIndex] != 0) {
						tmpRowspan = rowspanArr[tmpIndex];
						rowspanArr[tmpIndex] = 0;
						tmpHeader = cm.getColumnHeader(tmpIndex);
						thStr += "<th colspan=" + tmpColspan + " rowspan="
								+ tmpRowspan + ">" + tmpHeader + "</th>";
					}
				} else {
					thStr += "<th colspan=" + tmpColspan + " rowspan="
							+ tmpRowspan + ">" + tmpHeader + "</th>";
				}
			}
			thStr += "</tr>";
		}

		thStr += "<tr>";
		for ( var i2 = 0; i2 < cmLen; i2++) {
			if (rowspanArr[i2] == 1 && hiddenArr[i2] == 0) {
				thStr += "<th colspan=1 rowspan=1>" + cm.getColumnHeader(i2)
						+ "</th>";
			}
		}
		thStr += "</tr>";
	}
	return thStr;
}

document.write("<div style='display:none;'>"
		+ "<form id='excelForm' action='' method='post'>"
		+ "<textarea id='excelFormThead' name='thead'></textarea>"
		+ "<textarea id='excelFormTbody' name='tbody'></textarea>"
		+ "<textarea id='excelFormTitle' name='title'></textarea>"
		+ "<textarea id='excelFormFileName' name='fileName'></textarea>"
		+ "<textarea id='excelFormSheetName' name='sheetName'></textarea>"
		+ "<textarea id='excelFormWidths' name='widths'></textarea>"
		+ "<textarea id='excelFormHeights' name='heights'></textarea>"
		+ "<textarea id='excelFormRowColor' name='rowColor'></textarea>"
		+ "<textarea id='excelFormColColor' name='colColor'></textarea>"
		+ "<textarea id='excelFormIsSheet' name='isSheet'></textarea>"
		+ "<textarea id='excelFormSheetSize' name='sheetSize'></textarea>"
		+ "<textarea id='excelFormColName' name='colName'></textarea>"
		+ "<textarea id='excelFormDataIndex' name='dataIndex'></textarea>"
		+ "<textarea id='excelFormFormat' name='format'></textarea>"
		+ "<div id='excelFormParams'></div>" + "</form></div>");
