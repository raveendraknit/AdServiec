<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" /><link rel="icon" href="images/favicon.ico" type="image/x-icon" /><link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<title>:::Vicinity Ad Request API:::</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/codemirror.css" type="text/css" />
<link rel="stylesheet" href="css/freeformatter.css" type="text/css" />
</head>
<%  String SERVER_URL=com.sdk.common.util.PropertiesUtil.getProperty("SERVER_URL"); %>
<body>

<div id="main">

<div id="header">
<div class="header-sec">
<div class="logo"><a href="#"><img src="images/logo.png" width="178" height="86" border="0" alt="Vicinity" /></a></div>
</div>
<div id="menu">
<div class="menu-sec">
<div class="header-menu">
 <ul>
        <li><a href="#" class="active">Vicinity Ad Request API</a></li>
       
      </ul>
</div>
</div>
</div>
</div>

<div id="content-sec">
<div class="content-area"><h2>Welcome to Vicinity Ad Request API</h2>
<p>

This document describes how you can form server-side or client side ad requests to your mobile application. 
the Vicinity exposed API's to support this SDK communication will also form the standard XML input to request adverts for none-sdk based applications and the statspush xml will be the standard API interface to gather stats from applications. 
Customized Vicinity SDKs intended for featuring your apps with Vicinity mobile advertising are provided for various platforms as well.
 </p>

<div class="grid">
<p>
<b>PubReference:</b> This is a reference number we supply the publisher (app) so that when they make a request we know who it is.</p>

<p><b>AdvReference:</b> This is a unique advert request identifier supplied by the app.</p>

<p><b>Language (optional):</b>  this will default to English unless specified otherwise.</p>

<p><b>Location Data Type:</b> This can be GPS, BT (Blue Tooth), IPS (Indoor Positioning System), Address,  Wi-Fi or TWi-Fi (Triangulation), CT (Cell Tower) or TCT (Cell Tower Triangulation), Region.  Each type will have an appropriate array.</p> 

<p><b>Lat (latitude):</b> Obvious</p>

<p><b>Lon (Longitude):</b> Obvious</p>

<p><b>MSISND:</b> Obvious</p>

<p><b>IMEI:</b> Obvious</p>

<p><b>Advert Type:</b> This can be a banner, widget, pin, splash page or multiple other rich digital media. For the initial prototype we have "banner" defined</p>

<p><b>ScreenSize:</b> Measured in pixels - this market is fragmented so we need this to know what size to send.</p>

<p><b>Format:</b> The app specifies the digital media format it is expecting and supports.</p>

<p><b>Orientation:</b> The app specifies if the where advert will be located </p>

<p><b>VirtSize:</b> Specifies vertical size of the advert. (in pixels or stretch)</p>

<p><b>Social:</b> if available on OS - capture running social network and capture UID.**see captured from OS.</p>
 <p><b>Sample code and documents:</b> <a href="<%=SERVER_URL %>/samplecode/AccessAPI.zip">download</a></p>
</div>

<div>
<h2>Vicinity XML API</h2>

<label for="request_xml" class="control-label">Resource URI:{getAd} </label>
<div style="border: 1px dashed #2F6FAB; font-family: courier; padding: 0.1em 1em 0 1em; background-color: #F9F9F9;">
POST <span class="tag"><%=SERVER_URL %>/Api/xml-api/getAd</span> <br></br>
Content-Type <span class="tag">application/xml</span>
</div>



</div>
<br />

<div>

<div class="control-group">
						
						
						<div class="controls">
<div id="output">
						<h3>Request XML:</h3>
						<div id="xmlOutputWrapper">
							<pre class="xml prettyprint" id="xmlOutput"><span class="pun">&lt;?</span><span class="pln">xml version</span><span class="pun">=</span><span class="str">"1.0"</span><span class="pln"> encoding</span><span class="pun">=</span><span class="str">"UTF-8"</span><span class="pun">?&gt;</span><span class="pln">
</span><span class="tag">&lt;AdRequest&gt;</span><span class="pln">
   </span><span class="tag">&lt;type&gt;</span><span class="pln">banner</span><span class="tag">&lt;/type&gt;</span><span class="pln">
   </span><span class="tag">&lt;pubReference&gt;</span><span class="pln">E1F72E92-0AAD-4535-9689-EE284EF696AA</span><span class="tag">&lt;/pubReference&gt;</span><span class="pln">
   </span><span class="tag">&lt;advReference&gt;</span><span class="pln">F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB</span><span class="tag">&lt;/advReference&gt;</span><span class="pln">
   </span><span class="tag">&lt;ipAddr&gt;</span><span class="pln">12.76.8.9</span><span class="tag">&lt;/ipAddr&gt;</span><span class="pln">
   </span><span class="tag">&lt;ScreenSize&gt;</span><span class="pln">440x254</span><span class="tag">&lt;/ScreenSize&gt;</span><span class="pln">
   </span><span class="tag">&lt;bannerWidth&gt;</span><span class="pln">440</span><span class="tag">&lt;/bannerWidth&gt;</span><span class="pln">
   </span><span class="tag">&lt;bannerHeight&gt;</span><span class="pln">254</span><span class="tag">&lt;/bannerHeight&gt;</span><span class="pln">
   </span><span class="tag">&lt;virtSize&gt;</span><span class="pln">0</span><span class="tag">&lt;/virtSize&gt;</span><span class="pln">
   </span><span class="tag">&lt;horiSize&gt;</span><span class="pln">?</span><span class="tag">&lt;/horiSize&gt;</span><span class="pln">
   </span><span class="tag">&lt;formatUrl&gt;</span><span class="pln">?</span><span class="tag">&lt;/formatUrl&gt;</span><span class="pln">
   </span><span class="tag">&lt;refresh&gt;</span><span class="pln">30</span><span class="tag">&lt;/refresh&gt;</span><span class="pln">
   </span><span class="tag">&lt;longitude&gt;</span><span class="pln">28.0589131574218</span><span class="tag">&lt;/longitude&gt;</span><span class="pln">
   </span><span class="tag">&lt;latitude&gt;</span><span class="pln">-26.1082456957564</span><span class="tag">&lt;/latitude&gt;</span><span class="pln">
   </span><span class="tag">&lt;encryptType&gt;</span><span class="pln">?</span><span class="tag">&lt;/encryptType&gt;</span><span class="pln">
   </span><span class="tag">&lt;networkId&gt;</span><span class="pln">?</span><span class="tag">&lt;/networkId&gt;</span><span class="pln">
   </span><span class="tag">&lt;imei&gt;</span><span class="pln">357559048224673</span><span class="tag">&lt;/imei&gt;</span><span class="pln">
   </span><span class="tag">&lt;msisdn&gt;</span><span class="pln">27828781462</span><span class="tag">&lt;/msisdn&gt;</span><span class="pln">
   </span><span class="tag">&lt;socialType&gt;</span><span class="pln">FB</span><span class="tag">&lt;/socialType&gt;</span><span class="pln">
   </span><span class="tag">&lt;orientation&gt;</span><span class="pln">bottom</span><span class="tag">&lt;/orientation&gt;</span><span class="pln">
</span><span class="tag">&lt;/AdRequest&gt;</span></pre>
						</div>
											
					</div>
					</div>
					
					
					
					</div>
					
					
					
					
					<div class="control-group">
						
						
						<div class="controls">
	
					<div id="output">
					<br />
						<h3>Response XML:</h3>
						<div id="xmlOutputWrapper">
							<pre class="xml prettyprint" id="xmlOutput"><span class="pun">&lt;?</span><span class="pln">xml version</span><span class="pun">=</span><span class="str">"1.0"</span><span class="pln"> encoding</span><span class="pun">=</span><span class="str">"UTF-8"</span><span class="pun">?&gt;</span><span class="pln">
</span><span class="tag">&lt;rAdContent&gt;</span><span class="pln">
   </span><span class="tag">&lt;type&gt;</span><span class="pln">banner</span><span class="tag">&lt;/type&gt;</span><span class="pln">
   </span><span class="tag">&lt;name&gt;</span><span class="pln">Telkom Test</span><span class="tag">&lt;/name&gt;</span><span class="pln">
   </span><span class="tag">&lt;text&gt;</span><span class="pln">&amp;lt;body style="text-align:center;margin:0;padding:0;"&amp;gt;&amp;lt;div align="center"&amp;gt;&amp;lt;a id="PhoneClickAdLink" href="http://ad.vic-m.co:8080/AdService/clickAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;amp;url=http://piltd.com" target="_self"&amp;gt;Telkom Test&amp;lt;/a&amp;gt;&amp;lt;/div&amp;gt;&amp;lt;/body&amp;gt;</span><span class="tag">&lt;/text&gt;</span><span class="pln">
   </span><span class="tag">&lt;images</span><span class="pln"> </span><span class="atn">groupName</span><span class="pun">=</span><span class="atv">"Intro Banner"</span><span class="tag">&gt;</span><span class="pln">
      </span><span class="tag">&lt;fileName&gt;</span><span class="pln">360x73.png</span><span class="tag">&lt;/fileName&gt;</span><span class="pln">
      </span><span class="tag">&lt;height&gt;</span><span class="pln">73</span><span class="tag">&lt;/height&gt;</span><span class="pln">
      </span><span class="tag">&lt;imgInHtml&gt;</span><span class="pln">&amp;lt;img src="http://ad.vic-m.co:8080/AdService/fileHandleAction?file=FABDC38B-FEA8-461B-9F3B-23F3F8CCBFF5" width="360" height="73" /&amp;gt;</span><span class="tag">&lt;/imgInHtml&gt;</span><span class="pln">
      </span><span class="tag">&lt;imgSrc&gt;</span><span class="pln">http://ad.vic-m.co:8080/AdService/fileHandleAction?file=FABDC38B-FEA8-461B-9F3B-23F3F8CCBFF5</span><span class="tag">&lt;/imgSrc&gt;</span><span class="pln">
      </span><span class="tag">&lt;width&gt;</span><span class="pln">360</span><span class="tag">&lt;/width&gt;</span><span class="pln">
   </span><span class="tag">&lt;/images&gt;</span><span class="pln">
   </span><span class="tag">&lt;images</span><span class="pln"> </span><span class="atn">groupName</span><span class="pun">=</span><span class="atv">"Landing Page"</span><span class="tag">&gt;</span><span class="pln">
      </span><span class="tag">&lt;fileName&gt;</span><span class="pln">440x254.png</span><span class="tag">&lt;/fileName&gt;</span><span class="pln">
      </span><span class="tag">&lt;height&gt;</span><span class="pln">254</span><span class="tag">&lt;/height&gt;</span><span class="pln">
      </span><span class="tag">&lt;imgInHtml&gt;</span><span class="pln">&amp;lt;img src="http://ad.vic-m.co:8080/AdService/fileHandleAction?file=F0FF76F3-0F6D-4046-A575-E460DD86CBE4" width="440" height="254" /&amp;gt;</span><span class="tag">&lt;/imgInHtml&gt;</span><span class="pln">
      </span><span class="tag">&lt;imgSrc&gt;</span><span class="pln">http://ad.vic-m.co:8080/AdService/fileHandleAction?file=F0FF76F3-0F6D-4046-A575-E460DD86CBE4</span><span class="tag">&lt;/imgSrc&gt;</span><span class="pln">
      </span><span class="tag">&lt;width&gt;</span><span class="pln">440</span><span class="tag">&lt;/width&gt;</span><span class="pln">
   </span><span class="tag">&lt;/images&gt;</span><span class="pln">
   </span><span class="tag">&lt;actions&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionId&gt;</span><span class="pln">6</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Coupon</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
      </span><span class="tag">&lt;bannerActionId&gt;</span><span class="pln">CB647530-3EE2-4448-9B79-8FCA94CC1549</span><span class="tag">&lt;/bannerActionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;/actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;actions&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionId&gt;</span><span class="pln">5</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Call</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
      </span><span class="tag">&lt;bannerActionId&gt;</span><span class="pln">8F2150C8-B7AA-4EF0-9C6E-679C16546B5A</span><span class="tag">&lt;/bannerActionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;/actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;actions&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionId&gt;</span><span class="pln">1</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Drive to</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
      </span><span class="tag">&lt;bannerActionId&gt;</span><span class="pln">FEDBC43E-9D7B-478D-AB7D-8EDC45574CD4</span><span class="tag">&lt;/bannerActionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;/actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;actions&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionId&gt;</span><span class="pln">2</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Walk to</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
      </span><span class="tag">&lt;bannerActionId&gt;</span><span class="pln">7F9E5102-F849-4598-A1D5-099469334EC6</span><span class="tag">&lt;/bannerActionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;/actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;actions&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionId&gt;</span><span class="pln">4</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
      </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Web</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
      </span><span class="tag">&lt;bannerActionId&gt;</span><span class="pln">44C15D9D-F84C-4439-8F32-1AB1B19EE538</span><span class="tag">&lt;/bannerActionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;/actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;clickUrl&gt;</span><span class="pln">http://ad.vic-m.co:8080/AdService/clickAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;amp;url=http://piltd.com</span><span class="tag">&lt;/clickUrl&gt;</span><span class="pln">
   </span><span class="tag">&lt;trackUrl&gt;</span><span class="pln">http://ad.vic-m.co:8080/AdService/trackAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;amp;t=track.gif</span><span class="tag">&lt;/trackUrl&gt;</span><span class="pln">
   </span><span class="tag">&lt;clickType&gt;</span><span class="pln">INAPP</span><span class="tag">&lt;/clickType&gt;</span><span class="pln">
   </span><span class="tag">&lt;urlType&gt;</span><span class="pln">link</span><span class="tag">&lt;/urlType&gt;</span><span class="pln">
   </span><span class="tag">&lt;refresh&gt;</span><span class="pln">30</span><span class="tag">&lt;/refresh&gt;</span><span class="pln">
   </span><span class="tag">&lt;scale&gt;</span><span class="pln">false</span><span class="tag">&lt;/scale&gt;</span><span class="pln">
   </span><span class="tag">&lt;skippreflight&gt;</span><span class="pln">true</span><span class="tag">&lt;/skippreflight&gt;</span><span class="pln">
</span><span class="tag">&lt;/rAdContent&gt;</span></pre>
						</div>
												
					</div>	
					</div>

</div>
</div>
<br></br>

<div>


<label for="request_xml" class="control-label">Resource URI:{getAction} </label>
<div style="border: 1px dashed #2F6FAB; font-family: courier; padding: 0.1em 1em 0 1em; background-color: #F9F9F9;">
POST <span class="tag"><%=SERVER_URL %>/Api/xml-api/getAction</span> <br></br>
Content-Type <span class="tag">application/xml</span>
</div>



</div>
<br />

<div>

<div class="control-group">
						
						
						<div class="controls">
<div id="output">
						<h3>Request XML:</h3>
						<div id="xmlOutputWrapper">
						
						<pre class="xml prettyprint" id="xmlOutput"><span class="pun">&lt;?</span><span class="pln">xml version</span><span class="pun">=</span><span class="str">"1.0"</span><span class="pln"> encoding</span><span class="pun">=</span><span class="str">"UTF-8"</span><span class="pun">?&gt;</span><span class="pln">
</span><span class="tag">&lt;AdRequest&gt;</span><span class="pln">
   </span><span class="tag">&lt;pubReference&gt;</span><span class="pln">E1F72E92-0AAD-4535-9689-EE284EF696AA</span><span class="tag">&lt;/pubReference&gt;</span><span class="pln">
   </span><span class="tag">&lt;advReference&gt;</span><span class="pln">F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB</span><span class="tag">&lt;/advReference&gt;</span><span class="pln">
   </span><span class="tag">&lt;ipAddr&gt;</span><span class="pln">123.33.44.4</span><span class="tag">&lt;/ipAddr&gt;</span><span class="pln">
   </span><span class="tag">&lt;actionId&gt;</span><span class="pln">5</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;imei&gt;</span><span class="pln">357559048224673</span><span class="tag">&lt;/imei&gt;</span><span class="pln">
   </span><span class="tag">&lt;longitude&gt;</span><span class="pln">28.0589131574218</span><span class="tag">&lt;/longitude&gt;</span><span class="pln">
   </span><span class="tag">&lt;latitude&gt;</span><span class="pln">-26.1082456957564</span><span class="tag">&lt;/latitude&gt;</span><span class="pln">
   </span><span class="tag">&lt;msisdn&gt;</span><span class="pln">27828781462</span><span class="tag">&lt;/msisdn&gt;</span><span class="pln">
   </span><span class="tag">&lt;socialType&gt;</span><span class="pln">FB</span><span class="tag">&lt;/socialType&gt;</span><span class="pln">
</span><span class="tag">&lt;/AdRequest&gt;</span></pre>
						</div>
											
					</div>
					</div>
					
					
					
					</div>
					
					
					
					
					<div class="control-group">
						
						
						<div class="controls">
	
					<div id="output">
					<br />
						<h3>Response XML:</h3>
						<div id="xmlOutputWrapper">
							
						<pre class="xml prettyprint" id="xmlOutput"><span class="pun">&lt;?</span><span class="pln">xml version</span><span class="pun">=</span><span class="str">"1.0"</span><span class="pln"> encoding</span><span class="pun">=</span><span class="str">"UTF-8"</span><span class="pun">?&gt;</span><span class="pln">
</span><span class="tag">&lt;actions&gt;</span><span class="pln">
   </span><span class="tag">&lt;actionId&gt;</span><span class="pln">5</span><span class="tag">&lt;/actionId&gt;</span><span class="pln">
   </span><span class="tag">&lt;actionName&gt;</span><span class="pln">Call</span><span class="tag">&lt;/actionName&gt;</span><span class="pln">
   </span><span class="tag">&lt;actionValue&gt;</span><span class="pln">919990058204</span><span class="tag">&lt;/actionValue&gt;</span><span class="pln">
</span><span class="tag">&lt;/actions&gt;</span></pre>	
							
							
						</div>
												
					</div>	
					</div>

</div>
</div>



<div>
<br></br>
<h2>Vicinity JSON API</h2>

<label for="request_xml" class="control-label">Resource URI:{getAd} </label>
<div style="border: 1px dashed #2F6FAB; font-family: courier; padding: 0.1em 1em 0 1em; background-color: #F9F9F9;">
POST <span class="tag"><%=SERVER_URL %>/Api/json-api/getAd</span> <br></br>
Content-Type <span class="tag">application/json</span>
</div>



</div>
<br />

<div>

<div class="control-group">
						
						
						<div class="controls">
<div id="output">
						<h3>Request JSON:</h3>
						<div id="xmlOutputWrapper">
							<pre class="json" id="jsonOutput"><span class="json-open-bracket">{</span><img collapseid="1" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-1"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"pubReference"</span><span class="json-semi-colon">: </span><span class="json-value">"E1F72E92-0AAD-4535-9689-EE284EF696AA"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"advReference"</span><span class="json-semi-colon">: </span><span class="json-value">"F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"type"</span><span class="json-semi-colon">: </span><span class="json-value">"banner"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"ipAddr"</span><span class="json-semi-colon">: </span><span class="json-value">"122.33.87.67"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"ScreenSize"</span><span class="json-semi-colon">: </span><span class="json-value">"440x254"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerWidth"</span><span class="json-semi-colon">: </span><span class="json-value">"400"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerHeight"</span><span class="json-semi-colon">: </span><span class="json-value">"254"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"latitude"</span><span class="json-semi-colon">: </span><span class="json-value">"-33.940390"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"longitude"</span><span class="json-semi-colon">: </span><span class="json-value">"33.940390"</span><br>
<span class="json-indent"></span></span><span class="json-close-bracket">}</span></pre>
						</div>
											
					</div>
					</div>
					
					
					
					</div>
					
					
					
					
					<div class="control-group">
						
						
						<div class="controls">
	
					<div id="output">
					<br />
						<h3>Response JSON:</h3>
						<div id="xmlOutputWrapper">
							<pre class="json" id="jsonOutput"><span class="json-open-bracket">{</span><img collapseid="1" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-1"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"type"</span><span class="json-semi-colon">: </span><span class="json-value">"banner"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"name"</span><span class="json-semi-colon">: </span><span class="json-value">"Telkom Test"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"text"</span><span class="json-semi-colon">: </span><span class="json-value">"&lt;body style=\"text-align:center;margin:0;padding:0;\"&gt;&lt;div align=\"center\"&gt;&lt;a id=\"PhoneClickAdLink\" href=\"http://ad.vic-m.co:8080/AdService/clickAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;url=http://piltd.com\" target=\"_self\"&gt;Telkom Test&lt;\/a&gt;&lt;\/div&gt;&lt;\/body&gt;"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"images"</span><span class="json-semi-colon">: </span><span class="json-open-bracket">[</span><img collapseid="2" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-2"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="3" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-3"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"@groupName"</span><span class="json-semi-colon">: </span><span class="json-value">"Intro Banner"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"fileName"</span><span class="json-semi-colon">: </span><span class="json-value">"360x73.png"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"height"</span><span class="json-semi-colon">: </span><span class="json-value">"73"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"imgInHtml"</span><span class="json-semi-colon">: </span><span class="json-value">"&lt;img src=\"http://ad.vic-m.co:8080/AdService/fileHandleAction?file=FABDC38B-FEA8-461B-9F3B-23F3F8CCBFF5\" width=\"360\" height=\"73\" /&gt;"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"imgSrc"</span><span class="json-semi-colon">: </span><span class="json-value">"http://ad.vic-m.co:8080/AdService/fileHandleAction?file=FABDC38B-FEA8-461B-9F3B-23F3F8CCBFF5"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"width"</span><span class="json-semi-colon">: </span><span class="json-value">"360"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="4" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-4"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"@groupName"</span><span class="json-semi-colon">: </span><span class="json-value">"Landing Page"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"fileName"</span><span class="json-semi-colon">: </span><span class="json-value">"440x254.png"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"height"</span><span class="json-semi-colon">: </span><span class="json-value">"254"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"imgInHtml"</span><span class="json-semi-colon">: </span><span class="json-value">"&lt;img src=\"http://ad.vic-m.co:8080/AdService/fileHandleAction?file=F0FF76F3-0F6D-4046-A575-E460DD86CBE4\" width=\"440\" height=\"254\" /&gt;"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"imgSrc"</span><span class="json-semi-colon">: </span><span class="json-value">"http://ad.vic-m.co:8080/AdService/fileHandleAction?file=F0FF76F3-0F6D-4046-A575-E460DD86CBE4"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"width"</span><span class="json-semi-colon">: </span><span class="json-value">"440"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">]</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actions"</span><span class="json-semi-colon">: </span><span class="json-open-bracket">[</span><img collapseid="5" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-5"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="6" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-6"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"5"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Call"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerActionId"</span><span class="json-semi-colon">: </span><span class="json-value">"8F2150C8-B7AA-4EF0-9C6E-679C16546B5A"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="7" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-7"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"1"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Drive to"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerActionId"</span><span class="json-semi-colon">: </span><span class="json-value">"FEDBC43E-9D7B-478D-AB7D-8EDC45574CD4"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="8" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-8"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"6"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Coupon"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerActionId"</span><span class="json-semi-colon">: </span><span class="json-value">"CB647530-3EE2-4448-9B79-8FCA94CC1549"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="9" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-9"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"4"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Web"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerActionId"</span><span class="json-semi-colon">: </span><span class="json-value">"44C15D9D-F84C-4439-8F32-1AB1B19EE538"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-open-bracket">{</span><img collapseid="10" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-10"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"2"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Walk to"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="json-property">"bannerActionId"</span><span class="json-semi-colon">: </span><span class="json-value">"7F9E5102-F849-4598-A1D5-099469334EC6"</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">}</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span></span><span class="json-close-bracket">]</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"clickUrl"</span><span class="json-semi-colon">: </span><span class="json-value">"http://ad.vic-m.co:8080/AdService/clickAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;url=http://piltd.com"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"trackUrl"</span><span class="json-semi-colon">: </span><span class="json-value">"http://ad.vic-m.co:8080/AdService/trackAction?rAdId=F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB&amp;t=track.gif"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"clickType"</span><span class="json-semi-colon">: </span><span class="json-value">"INAPP"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"urlType"</span><span class="json-semi-colon">: </span><span class="json-value">"link"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"refresh"</span><span class="json-semi-colon">: </span><span class="json-value">"30"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"scale"</span><span class="json-semi-colon">: </span><span class="json-value">"false"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"skippreflight"</span><span class="json-semi-colon">: </span><span class="json-value">"true"</span><br>
<span class="json-indent"></span></span><span class="json-close-bracket">}</span></pre>
						</div>
												
					</div>	
					</div>

</div>
</div>
<br></br>

<div>


<label for="request_xml" class="control-label">Resource URI:{getAction} </label>
<div style="border: 1px dashed #2F6FAB; font-family: courier; padding: 0.1em 1em 0 1em; background-color: #F9F9F9;">
POST <span class="tag"><%=SERVER_URL %>/Api/json-api/getAction</span> <br></br>
Content-Type <span class="tag">application/json</span>
</div>



</div>
<br />

<div>

<div class="control-group">
						
						
						<div class="controls">
<div id="output">
						<h3>Request JSON:</h3>
						<div id="xmlOutputWrapper">
						
						<pre class="json" id="jsonOutput"><span class="json-open-bracket">{</span><img collapseid="1" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-1"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"pubReference"</span><span class="json-semi-colon">: </span><span class="json-value">"E1F72E92-0AAD-4535-9689-EE284EF696AA"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"advReference"</span><span class="json-semi-colon">: </span><span class="json-value">"F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"5"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"ipAddr"</span><span class="json-semi-colon">: </span><span class="json-value">"122.33.87.67"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"latitude"</span><span class="json-semi-colon">: </span><span class="json-value">"-33.940390"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"longitude"</span><span class="json-semi-colon">: </span><span class="json-value">"18.465136"</span><br>
<span class="json-indent"></span></span><span class="json-close-bracket">}</span></pre>
						</div>
											
					</div>
					</div>
					
					
					
					</div>
					
					
					
					
					<div class="control-group">
						
						
						<div class="controls">
	
					<div id="output">
					<br />
						<h3>Response JSON:</h3>
						<div id="xmlOutputWrapper">
							
						<pre class="json" id="jsonOutput"><span class="json-open-bracket">{</span><img collapseid="1" src="gfx/minus.gif" class="json-minus"><span class="json-collapse-1"><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionId"</span><span class="json-semi-colon">: </span><span class="json-value">"5"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionName"</span><span class="json-semi-colon">: </span><span class="json-value">"Call"</span><span class="json-comma">,</span><br>
<span class="json-indent">&nbsp;&nbsp;&nbsp;</span><span class="json-property">"actionValue"</span><span class="json-semi-colon">: </span><span class="json-value">"919990058204"</span><br>
<span class="json-indent"></span></span><span class="json-close-bracket">}</span></pre>
							
							
						</div>
												
					</div>	
					
					<br></br>
					 <p><b>Sample code and documents:</b> <a href="<%=SERVER_URL %>/samplecode/AccessAPI.zip">download</a></p>
					</div>

</div>
</div>


</div>

</div>

<div id="footer">
<div class="footer-text">© 2013 Company, All Rights Reserved.</div>
</div>

</div>

</body>
</html>
