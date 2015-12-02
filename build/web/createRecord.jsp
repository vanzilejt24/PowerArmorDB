<%-- 
    Document   : createRecord
    Created on : Nov 3, 2015, 5:19:26 PM
    Author     : Jon VanZile - modified from John Phillips' code
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Power Armor Data Base</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
        
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

  <script src="//code.jquery.com/jquery-1.10.2.js"></script>

  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

  <link rel="stylesheet" href="/resources/demos/style.css">


    <!--    <script>
            $(function() {
                $("#datepicker").datepicker({dateFormat: "yy-mm-dd"}).datepicker("setDate", new Date());
            });
            </script>
    -->
    </head>
    <body>
        <h1><a href="home.html">Power Armor Data Base</a></h1>
        <h2>Add A New Power Armor Component</h2>
        <form action="create" method="get">

            Model Name: <input type="text" name="modelName" size="15" >
            <br><br>
            Equipment Slot: <input type="text" name="slot" size="10" >
            <br><br>            
            Paint Job: <input type="text" name="paint" size="30" >
            <br><br>
            Physical Damage Resist: <input type="text" name="dResist" >
            <br><br>
            Energy Resist: <input type="text" name="eResist" >
            <br><br>
            Radiation Resist: <input type="text" name="rResist">
            <br><br>
            Location Found: <input type="text" name="location" size="30"> 
            <br><br>
            <input type="hidden" name="action" value="createRecord">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

