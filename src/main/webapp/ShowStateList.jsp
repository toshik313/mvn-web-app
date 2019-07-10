<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>目次画面</title>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.toshi313.dao.SelectMtState" %>
<%
    ArrayList<HashMap<String, String>> mt_state_list = (ArrayList<HashMap<String, String>>)request.getAttribute("mt_state_list");
%>
</head>
<body>

<h1>都道府県一覧</h1>
<%  if(mt_state_list == null) { %>
  <p>都道府県一覧は登録されていません。</p>
<%  } else { %>
  <table>
    <tr>
      <th>都道府県コード</th>
      <th>都道府県名</th>
      <th>更新日時</th>
    </tr>
<%      for(int i = 0 ; i < mt_state_list.size() ; i ++) {
            HashMap<String, String> map = mt_state_list.get(i);
            String state_code = map.get(SelectMtState.COL_NAMES[SelectMtState.COL_INDEX_STATE_CODE]);
            String state_name = map.get(SelectMtState.COL_NAMES[SelectMtState.COL_INDEX_STATE_NAME]);
            String update_datetime = map.get(SelectMtState.COL_NAMES[SelectMtState.COL_INDEX_UPDATE_DATETIME]);
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
