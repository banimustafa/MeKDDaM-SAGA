<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>

<xsl:template match="/project">
  <html>
 	<head>
  		<title> MeKDaM Project </title>
	</head>


	<body> <h1> MeKDaM Process Project </h1> <br/>
		<table border="2">
<!--
			<tr> <td>heading= </td>
                       <td> body </td> 
                  </tr>
-->
			<xsl:apply-templates/>
		</table>
	</body>
  </html>
</xsl:template>

<xsl:template match="name">
	<tr> <td>
		  <xsl:value-of select="heading"/>
	     </td> 

   	     <td> 
              <xsl:value-of select="body"/>
   	      </td>
	</tr>
</xsl:template>

<xsl:template match="location">
	<tr> <td>
		  <xsl:value-of select="heading"/>
	     </td> 

   	     <td> 
              <xsl:value-of select="body"/>
   	      </td>
	</tr>
</xsl:template>

<xsl:template match="date">
	<tr> <td>
		  <xsl:value-of select="heading"/>
	     </td> 

   	     <td> 
              <xsl:value-of select="body"/>
   	      </td>
	</tr>
</xsl:template>

<xsl:template match="description">
	<tr> <td>
		  <xsl:value-of select="heading"/>
	     </td> 

   	     <td> 
              <xsl:value-of select="body"/>
   	      </td>
	</tr>
</xsl:template>

<xsl:template match="information">
	<tr> <td>
		  <xsl:value-of select="heading"/>
	     </td> 

   	     <td> 
              <xsl:value-of select="body"/>
   	      </td>
	</tr>
</xsl:template>


</xsl:stylesheet>