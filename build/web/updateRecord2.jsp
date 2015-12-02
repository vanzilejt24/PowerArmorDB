<%-- 
    Document   : updateRecord2
    Created on : Nov 3, 2015, 8:54:49 PM
    Author     : Jon VanZile - modified from John Phillips' code
 
--%>

<%@page import="model.Parmor" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Phillips' Employee Web</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Employee Web</a></h1>
        <h2>Update Employee Record</h2>
        <form action="update" method="get">
            <% Parmor parmor = (Parmor) request.getAttribute("parmor");%>
            Power Armor Entry Id: <input type="text" name="id" value="<%= parmor.getId() %>" readonly>
            <br><br>
            Model Name: <input type="text" name="modelName" size="15" value="<%= parmor.getModelName() %>" required>
            <br><br>
            Equipment Slot: <input type="text" name="slot" size="10" value="<%= parmor.getSlot() %>" required>
            <br><br>            
            Paint Job: <input type="text" name="paint" size="30" value="<%= parmor.getPaint() %>" required>
            <br><br>
            Physical; Damage Resist: <input type="text" name="dResist" value="<%= parmor.getdResist() %>" required>
            <br><br>
            Energy Resist: <input type="text" name="eResist" value="<%= parmor.geteResist() %>" required>
            <br><br>
            Radiation Resist: <input type="text" name="rResist" value="<%= parmor.getrResist() %>" required>
            <br><br>
            Location: <input type="text" name="location" size="30" value="<%= parmor.getLocation() %>" required>
            <br><br>

            <input type="hidden" name="action" value="updateRecord2">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
