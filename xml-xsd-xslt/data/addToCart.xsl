<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:crt="http://www.example.org/cart/"
	xmlns:str="http://www.example.org/store/">
	<xsl:include href="updateCart.xsl"/>
	<xsl:param name="productName"/>
	<xsl:param name="units"/>
	<xsl:template match="/">
		<crt:cart>
			<xsl:if test="$productName != ''">
				<xsl:call-template name="addToCart"/>
			</xsl:if>
			<xsl:call-template name="updateCart"/>
		</crt:cart>
	</xsl:template>
	<xsl:template name="addToCart">
		<crt:item>
			<xsl:attribute name="productId">
				<xsl:variable name="store" select="document('store.xml')"/>
				<xsl:for-each select="$store//str:product[./@str:name = $productName]/@str:id">
					<xsl:copy/>
				</xsl:for-each>
			</xsl:attribute>
			<xsl:attribute name="units">
				<xsl:value-of select="$units"></xsl:value-of>
			</xsl:attribute>
		</crt:item>
	</xsl:template>
</xsl:stylesheet>