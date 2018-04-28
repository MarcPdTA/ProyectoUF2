<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
            <h1 style="text-align:center">Noticias</h1>
                <xsl:for-each select="noticias/noticia">

                    <h2 style="margin-top: 100px;"><xsl:value-of select="titulo"/></h2>
                    <h4><xsl:value-of select="autor"/></h4>
                    <xsl:for-each select="body/p">
                        <p><xsl:value-of select="." /></p>
                    </xsl:for-each>
                    <h5><xsl:value-of select="fecha"/></h5>
                </xsl:for-each>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>