<%-- 
    Document   : displayRecords
    Created on : Nov 3, 2015, 4:52:40 PM
    Author     : John Phillips
--%>

<%@page import="java.util.List, model.Parmor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Superstar Health Care</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Power Armor DB</a></h1>
        <h2>User Report</h2>
        <%
            List<Parmor> mydata = (List<Parmor>) request.getAttribute("mydata");
            out.println("<table>");
            for (Parmor parmor : mydata) {
                out.println(parmor.inHTMLRowFormat());
            }
            out.println("</table>");
        %>
    </body>
</html>
