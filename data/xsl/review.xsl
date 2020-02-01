<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="review_template">
        <xsl:for-each select="./paragraphs/paragraph" >
            <h2>
                <xsl:value-of select="./title" />
            </h2>
            <p>
                <xsl:value-of select="./text" />
            </p>
        </xsl:for-each>

    </xsl:template>

    <xsl:template match="/review">
        <html>
            <head>
                <title>
                    Review
                </title>
            </head>
            <body>
                <xsl:call-template name="review_template" />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="/reviews">
        <html>
            <head>
                <title>Reviews</title>
            </head>
            <body>
                <xsl:for-each select="/reviews/review">
                    <xsl:call-template name="review_template" />
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>



</xsl:stylesheet>