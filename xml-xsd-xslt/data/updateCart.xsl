<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:crt="http://www.example.org/cart/"
	xmlns:upd="http://www.example.org/updateCart">
	<xsl:param name="updates" select="empty"/>
	<xsl:template name="updateCart">
		<xsl:for-each select="$updates/upd:action">
			<xsl:if test="//crt:item[@crt:id = ./@upd:id]">
				<xsl:choose>
				<xsl:when test="./@upd:type = 'delete'">
				</xsl:when>
				<xsl:when test="./@upd:type = 'update'">
				</xsl:when>
			</xsl:choose>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>