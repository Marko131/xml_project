<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Cover letter</title>
            </head>
            <body>
                <!-- Header -->
                <table>
                    <tr>
                        <td>
                            <xsl:value-of select="./cover_letter/sender/name"/>
                        </td>
                        <td>
                            <xsl:value-of select="./cover_letter/receiver/name"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <xsl:value-of select="./cover_letter/sender/email"/>
                        </td>
                        <td>
                            <xsl:value-of select="./cover_letter/receiver/email"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <xsl:value-of select="./cover_letter/sender/phone_number"/>
                        </td>
                        <td>
                            <xsl:value-of select="./cover_letter/receiver/phone_number"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <xsl:value-of select="./cover_letter/sender/institution"/>
                        </td>
                        <td>
                            <xsl:value-of select="./cover_letter/receiver/institution"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <xsl:value-of select="./cover_letter/sender/address"/>
                        </td>
                        <td>
                            <xsl:value-of select="./cover_letter/receiver/address"/>
                        </td>
                    </tr>
                </table>

                <!-- Content -->
                <xsl:for-each select="./cover_letter/paragraphs/paragraph">
                    <p>
                        <xsl:value-of select="."/>
                    </p>
                </xsl:for-each>

                <!-- Signature -->
                <img>
                    <xsl:attribute name="src">
                        <xsl:value-of select="./cover_letter/signature"/>
                    </xsl:attribute>
                </img>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>