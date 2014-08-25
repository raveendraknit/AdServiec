<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
    <title>::: Dynamic Ad :::</title>
    <meta charset="utf-8" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/favicon.ico" />
    <style>
	#container
{
    text-align: center;
}
#main
{
    display: inline-block;
}

</style>
</head>
<body>

<div id="container">
    <div id="main">
        <div id="somebackground">
        <%=request.getAttribute("html") %>
           </div>
    </div>
</div>


</body>
</html>