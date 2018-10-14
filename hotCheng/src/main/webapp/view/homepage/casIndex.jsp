<%@ page language="java"  session="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
final String queryString = request.getQueryString();
final String url = basePath+ "system/casLogin.action" + (queryString != null ? "?" + queryString : "");
response.sendRedirect(response.encodeURL(url));%>