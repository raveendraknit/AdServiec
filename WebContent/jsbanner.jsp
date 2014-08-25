<% String landingurl=(String)request.getAttribute("landingurl");
 String imgSrc=(String)request.getAttribute("imgSrc") ;
String width=(String)request.getAttribute("width");
String height=(String)request.getAttribute("height"); %>
document.write("<a href='<%=landingurl %>' target='_blank'><img src='<%=imgSrc %>' width='<%=width %>' height='<%=height %>'  /></a>");