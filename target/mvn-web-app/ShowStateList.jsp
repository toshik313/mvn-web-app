<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>都道府県一覧画面</title>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.toshi313.dao.SelectMtState" %>
<%
    ArrayList<HashMap<String, String>> mtStateList = (ArrayList<HashMap<String, String>>)request.getAttribute("mtStateList");
%>
</head>
<body>
<h1>都道府県一覧画面</h1>

<%  if(mtStateList == null) { %>
  <p>都道府県一覧は登録されていません。</p>
<%  } else { %>
  <table>
    <tr>
      <th>都道府県コード</th>
      <th>都道府県名</th>
      <th>更新日時</th>
    </tr>
<%      for(int i = 0 ; i < mtStateList.size() ; i ++) {
            HashMap<String, String> map = mtStateList.get(i);
            String state_code = map.get(SelectMtState.getColNames(SelectMtState.COL_INDEX_STATE_CODE));
            String state_name = map.get(SelectMtState.getColNames(SelectMtState.COL_INDEX_STATE_NAME));
            String update_datetime = map.get(SelectMtState.getColNames(SelectMtState.COL_INDEX_UPDATE_DATETIME));
%>
    <tr>
      <td><%= state_code %></td>
      <td><%= state_name %></td>
      <td><%= update_datetime %></td>
    </tr>
<%      } %>
  </table>
<%  } %>

</body>
</html>
