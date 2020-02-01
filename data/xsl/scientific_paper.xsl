<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xms="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="xs"
                version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    body {
                    margin-left: 20%;
                    margin-right: 20%;
                    font-size: 20px;
                    }
                    .paper-title {
                    font-size: 40px;
                    text-align: center;
                    }
                    .box {
                    margin-top: 20px;
                    width: 60%;
                    margin-left: auto;
                    margin-right: auto;
                    border: 2px solid black;
                    padding: 10px;
                    }
                    .box-title {
                    text-align: center;
                    font-size: 25px;
                    font-weight: bold;
                    }
                    .box-container {
                    display: flex;
                    }
                    .box-item {
                    align-self: center;
                    }
                    .box-item p {
                    width: 100%
                    margin: 10px;
                    }
                    .box-image {
                    margin: 10px;
                    }
                    .box-image img {
                    width: 20vw;
                    }

                    .author-name {
                    font-size: 20px;
                    text-align:center;
                    }
                    .author-about {
                    font-style: italic;
                    text-align: center;
                    }
                    .keyword {
                    background-color: #add8e6;
                    border-radius: 15px;
                    padding: 7px;
                    }
                    .quote {
                    text-align: center;
                    font-style: italic;
                    }
                    .quote-source {
                    text-align: right;
                    }
                    .bold {
                    font-weight: bold;
                    }

                </style>
                <title>Scientific Paper</title>
            </head>
            <body>
                <!-- Paper title -->
                <h1 class="paper-title">
                    <xsl:value-of select="./paper/paper_title"/>
                </h1>
                <!-- Authors -->
                <xsl:for-each select="./paper/authors/author">
                    <div class="author-info">
                        <p class="author-name">
                            <xsl:value-of select="name"/>
                        </p>
                        <p class="author-about">
                            <xsl:value-of select="university"/>
                            <xsl:value-of select="city"/>
                            <xsl:value-of select="country"/>
                        </p>
                    </div>
                </xsl:for-each>
                <!-- Abstract -->
                <p>
                    <xsl:for-each select="./paper/abstract/abstract_element">
                        <p>
                            <span class="bold">
                                <xsl:value-of select="current()/@title"/>
                            </span>
                            <span>
                                <xsl:value-of select="current()"/>
                            </span>
                        </p>
                    </xsl:for-each>
                </p>
                <!-- Keywords -->
                <h2>Keywords</h2>
                <p>
                    <xsl:for-each select="./paper/abstract/keywords/keyword">
                        <span class="keyword">
                            <xsl:value-of select="."/>
                        </span>
                    </xsl:for-each>
                </p>
                <!-- Content -->
                <div class="content">
                    <xsl:for-each select="./paper/section">
                        <h2>
                            <xsl:value-of select="./@title"/>
                        </h2>
                        <div>
                            <xsl:call-template name="section_content"/>
                        </div>

                        <xsl:for-each select="./section">
                            <h3>
                                <xsl:value-of select="./@title"/>
                            </h3>
                            <div>
                                <xsl:call-template name="section_content"/>
                            </div>
                            <xsl:for-each select="./section">
                                <h4>
                                    <xsl:value-of select="./@title"/>
                                </h4>
                                <div>
                                    <xsl:call-template name="section_content"/>
                                </div>
                                <xsl:for-each select="./section">
                                    <h5>
                                        <xsl:value-of select="./@title"/>
                                    </h5>
                                    <xsl:for-each select="./section">
                                        <h6>
                                            <xsl:value-of select="./@title"/>
                                        </h6>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                        </xsl:for-each>
                    </xsl:for-each>
                </div>

            </body>
        </html>
    </xsl:template>

    <xsl:template name="section_content">
        <xsl:for-each select="./paragraph">
            <!-- Paragraph content -->
            <div>
                <xsl:value-of select="./text"/>
            </div>

            <div>
                <ul>
                    <xsl:for-each select="./list/item">
                        <li>
                            <xsl:value-of select="."/>
                        </li>
                    </xsl:for-each>
                </ul>
            </div>

            <!--
            <div>
                <xsl:for-each select="./link">

                    <xsl:value-of select="./image" />
                </xsl:for-each>
            </div>
            -->


            <div>
                <xsl:for-each select="./quote">
                    <div>
                        <p class="quote">
                            <xsl:value-of select="./quote_text"/>
                        </p>
                        <p class="quote-source">
                            <span>
                                <xsl:value-of select="./source/source_author"/>
                            </span>
                            <span>
                                <xsl:value-of select="./source/source_title"/>
                            </span>
                            <span>
                                <xsl:value-of select="./source/source_page"/>
                            </span>
                        </p>
                    </div>
                </xsl:for-each>
            </div>


            <!--
            <div>
                <xsl:value-of select="./ref" />
            </div>
            -->

        </xsl:for-each>
        <xsl:for-each select="./box">
            <div class="box">
                <div class="box-title">
                    <span>
                        <xsl:value-of select="./@title"/>
                    </span>
                    <span>
                        <xsl:value-of select="./@description"/>
                    </span>
                </div>

                <div class="box-container">
                    <div class="box-item box-image">
                        <img>
                            <xsl:attribute name="src">
                                <xsl:value-of select="./image"/>
                            </xsl:attribute>
                        </img>
                    </div>
                    <div class="box-item">
                        <p>
                            <xsl:value-of select="./textbox"/>
                        </p>
                    </div>
                </div>
            </div>
        </xsl:for-each>
        <xsl:for-each select="./quote">
            <p class="quote">
                <xsl:value-of select="./quote_text"/>
            </p>
            <p class="quote-source">
                <span>
                    <xsl:value-of select="./source/source_author"/>
                </span>
                <span>
                    <xsl:value-of select="./source/source_title"/>
                </span>
                <span>
                    <xsl:value-of select="./source/source_page"/>
                </span>

            </p>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>