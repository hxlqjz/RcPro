jQuery(document).ready(function(){  
			    //combotree可编辑，自定义模糊查询  
			    $.fn.combotree.defaults.editable = true;  
			    $.extend($.fn.combotree.defaults.keyHandler,{  
			        up:function(){  
			            console.log('up');  
			        },  
			        down:function(){  
			            console.log('down');  
			        },  
			        enter:function(){  
			            console.log('enter');  
			        },  
			        query:function(q){  
			            var t = $(this).combotree('tree');  
			            var nodes = t.tree('getChildren');  
			            for(var i=0; i<nodes.length; i++){  
			                var node = nodes[i];  
			                if (node.text.indexOf(q) >= 0){  
			                    $(node.target).show();  
			                } else {  
			                    $(node.target).hide();  
			                }  
			            }  
			            var opts = $(this).combotree('options');  
			            if (!opts.hasSetEvents){  
			                opts.hasSetEvents = true;  
			                var onShowPanel = opts.onShowPanel;  
			                opts.onShowPanel = function(){  
			                    var nodes = t.tree('getChildren');  
			                    for(var i=0; i<nodes.length; i++){  
			                        $(nodes[i].target).show();  
			                    }  
			                    onShowPanel.call(this);  
			                };  
			                $(this).combo('options').onShowPanel = opts.onShowPanel;  
			            }  
			        }  
			    });  
			});  
//扩展datagrid 合并相同行
$.extend($.fn.datagrid.methods, {
    autoMergeCells: function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
//                    } else {
//                        var tfv = tf[row[field]];
//                        if (tfv) {
//                            tfv.push(i);
//                        } else {
//                            tfv = tf[row[field]] = [i];
//                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
 
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index: megerIndex,
                                    field: field,
                                    rowspan: rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});

/**
 * easyui-datagrid编辑表格扩展
 */
$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var j=0; j<fields.length; j++){
				var col1 = $(this).datagrid('getColumnOption', fields[j]);
				col1.editor = col1.editor1;
			}
		});
	}
});