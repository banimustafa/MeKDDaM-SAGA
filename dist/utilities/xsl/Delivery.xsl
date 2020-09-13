<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fo="http://www.w3.org/1999/XSL/Format"
  version="1.0">

<xsl:template match="delivery">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

  <fo:layout-master-set>

    <fo:simple-page-master master-name="all"
            page-height="11.5in" 
            page-width="8.5in"
            margin-top="1in" 
            margin-bottom="1in"
            margin-left="0.75in" 
            margin-right="0.75in">
    <fo:region-body margin-top="1in" margin-bottom="0.75in"/>
    <fo:region-before extent="0.75in"/>
    <fo:region-after extent="0.5in"/>
  </fo:simple-page-master>

  </fo:layout-master-set>

  <fo:page-sequence master-reference="all" format="i">

<!-- Document Mean Header-->
<fo:static-content flow-name="xsl-region-before">
       <fo:block text-align="center" font-size="20pt" font-family="serif"
       line-height="14pt" >
        Phase Delivery
       </fo:block>
     </fo:static-content>

 <!-- Page Number 0-->
    <fo:static-content flow-name="xsl-region-after">
    <fo:block text-align="start"
      font-size="10pt" font-family="serif" line-height="1em + 2pt">
      Page (<fo:page-number/>)
      </fo:block>
    </fo:static-content>


 <!-- root Level 0-->
<fo:flow flow-name="xsl-region-body">
    <xsl:apply-templates select="section"/>
</fo:flow>

</fo:page-sequence>
</fo:root>
</xsl:template>


<!-- root Level 1-->
<xsl:template match="section">
  <fo:block text-align="start" font-size="12pt" font-family="sans-serif">
    <xsl:apply-templates select="title"/>
    <xsl:apply-templates select="para"/>
  </fo:block>
</xsl:template>


<!-- root Level 2-->
<xsl:template match="title">
  <fo:block text-align="start" font-size="16pt" color="blue" space-before.optimum="18pt">     
    <fo:marker  marker-class-name="title"><xsl:value-of select="."/></fo:marker>
     Section is:
    <xsl:value-of select="."/>
  </fo:block>
</xsl:template>

<!-- root Level 2-->
<xsl:template match="para">
  <fo:block text-align="start" font-size="12pt" start-indent="0em" color="black" space-before.optimum="6pt">      
    <fo:marker  marker-class-name="para"><xsl:value-of select="."/></fo:marker>
     Paragraph is
    <xsl:value-of select="."/>
  </fo:block>
</xsl:template>

</xsl:stylesheet>

