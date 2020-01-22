package tim10.project.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {

    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    @org.junit.Test
    public void testGetById() throws XMLDBException, JAXBException {
        System.out.println(scientificPaperRepository.getById("/db/sample/library", "paper1.xml").getAbstract().toString().toString().toString().toString());
    }

    @org.junit.Test
    public void testSave() throws XMLDBException, JAXBException, IOException {
        Reader reader = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<paper xmlns:vc=\"http://www.w3.org/2007/XMLSchema-versioning\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:noNamespaceSchemaLocation=\"file:/C:/Users/Strahinja/Desktop/Scientific_Paper.xsd\" date_received=\"2006-05-04\" date_revised=\"2006-05-04\" date_accepted=\"2006-05-04\">\n" +
                "    <paper_title>paper_title0</paper_title>\n" +
                "    <authors>\n" +
                "        <author>\n" +
                "            <name>name0</name>\n" +
                "            <university>university0</university>\n" +
                "            <city>city0</city>\n" +
                "            <state>state0</state>\n" +
                "            <country>country0</country>\n" +
                "        </author>\n" +
                "        <author>\n" +
                "            <name>name1</name>\n" +
                "            <university>university1</university>\n" +
                "            <city>city1</city>\n" +
                "            <state>state1</state>\n" +
                "            <country>country1</country>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract>\n" +
                "        <keywords>\n" +
                "            <keyword>keyword0</keyword>\n" +
                "            <keyword>keyword1</keyword>\n" +
                "        </keywords>\n" +
                "        <abstract_element title=\"title0\">abstract_element0</abstract_element>\n" +
                "        <abstract_element title=\"title1\">abstract_element1</abstract_element>\n" +
                "    </abstract>\n" +
                "    <quote>\n" +
                "        <quote_text>quote_text0</quote_text>\n" +
                "        <source>\n" +
                "            <source_author>source_author0</source_author>\n" +
                "            <source_title>source_title0</source_title>\n" +
                "            <source_page>source_page0</source_page>\n" +
                "        </source>\n" +
                "    </quote>\n" +
                "    <quote>\n" +
                "        <quote_text>quote_text1</quote_text>\n" +
                "        <source>\n" +
                "            <source_author>source_author1</source_author>\n" +
                "            <source_title>source_title1</source_title>\n" +
                "            <source_page>source_page1</source_page>\n" +
                "        </source>\n" +
                "    </quote>\n" +
                "    <section title=\"title2\">\n" +
                "        <paragraph>\n" +
                "            <ref id=\"50\">ref0</ref>\n" +
                "            <ref id=\"50\">ref1</ref>\n" +
                "            <list>\n" +
                "                <item>item0</item>\n" +
                "                <item>item1</item>\n" +
                "            </list>\n" +
                "            <list>\n" +
                "                <item>item2</item>\n" +
                "                <item>item3</item>\n" +
                "            </list>\n" +
                "            <image title=\"title3\" description=\"description0\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title4\" description=\"description1\">ZGVmYXVsdA==</image>\n" +
                "            <link href=\"href0\">\n" +
                "                <image title=\"title5\" description=\"description2\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href1\">\n" +
                "                <image title=\"title6\" description=\"description3\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text2</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author2</source_author>\n" +
                "                    <source_title>source_title2</source_title>\n" +
                "                    <source_page>source_page2</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text3</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author3</source_author>\n" +
                "                    <source_title>source_title3</source_title>\n" +
                "                    <source_page>source_page3</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <text>text0</text>\n" +
                "        </paragraph>\n" +
                "        <paragraph>\n" +
                "            <ref id=\"50\">ref2</ref>\n" +
                "            <ref id=\"50\">ref3</ref>\n" +
                "            <list>\n" +
                "                <item>item4</item>\n" +
                "                <item>item5</item>\n" +
                "            </list>\n" +
                "            <list>\n" +
                "                <item>item6</item>\n" +
                "                <item>item7</item>\n" +
                "            </list>\n" +
                "            <image title=\"title7\" description=\"description4\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title8\" description=\"description5\">ZGVmYXVsdA==</image>\n" +
                "            <link href=\"href2\">\n" +
                "                <image title=\"title9\" description=\"description6\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href3\">\n" +
                "                <image title=\"title10\" description=\"description7\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text4</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author4</source_author>\n" +
                "                    <source_title>source_title4</source_title>\n" +
                "                    <source_page>source_page4</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text5</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author5</source_author>\n" +
                "                    <source_title>source_title5</source_title>\n" +
                "                    <source_page>source_page5</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <text>text1</text>\n" +
                "        </paragraph>\n" +
                "        <box title=\"title11\" description=\"description8\">\n" +
                "            <link href=\"href4\">\n" +
                "                <image title=\"title12\" description=\"description9\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href5\">\n" +
                "                <image title=\"title13\" description=\"description10\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <image title=\"title14\" description=\"description11\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title15\" description=\"description12\">ZGVmYXVsdA==</image>\n" +
                "            <textbox>textbox0</textbox>\n" +
                "            <textbox>textbox1</textbox>\n" +
                "        </box>\n" +
                "        <box title=\"title16\" description=\"description13\">\n" +
                "            <link href=\"href6\">\n" +
                "                <image title=\"title17\" description=\"description14\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href7\">\n" +
                "                <image title=\"title18\" description=\"description15\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <image title=\"title19\" description=\"description16\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title20\" description=\"description17\">ZGVmYXVsdA==</image>\n" +
                "            <textbox>textbox2</textbox>\n" +
                "            <textbox>textbox3</textbox>\n" +
                "        </box>\n" +
                "        <quote>\n" +
                "            <quote_text>quote_text6</quote_text>\n" +
                "            <source>\n" +
                "                <source_author>source_author6</source_author>\n" +
                "                <source_title>source_title6</source_title>\n" +
                "                <source_page>source_page6</source_page>\n" +
                "            </source>\n" +
                "        </quote>\n" +
                "        <quote>\n" +
                "            <quote_text>quote_text7</quote_text>\n" +
                "            <source>\n" +
                "                <source_author>source_author7</source_author>\n" +
                "                <source_title>source_title7</source_title>\n" +
                "                <source_page>source_page7</source_page>\n" +
                "            </source>\n" +
                "        </quote>\n" +
                "    </section>\n" +
                "    <section title=\"title21\">\n" +
                "        <paragraph>\n" +
                "            <ref id=\"50\">ref4</ref>\n" +
                "            <ref id=\"50\">ref5</ref>\n" +
                "            <list>\n" +
                "                <item>item8</item>\n" +
                "                <item>item9</item>\n" +
                "            </list>\n" +
                "            <list>\n" +
                "                <item>item10</item>\n" +
                "                <item>item11</item>\n" +
                "            </list>\n" +
                "            <image title=\"title22\" description=\"description18\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title23\" description=\"description19\">ZGVmYXVsdA==</image>\n" +
                "            <link href=\"href8\">\n" +
                "                <image title=\"title24\" description=\"description20\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href9\">\n" +
                "                <image title=\"title25\" description=\"description21\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text8</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author8</source_author>\n" +
                "                    <source_title>source_title8</source_title>\n" +
                "                    <source_page>source_page8</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text9</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author9</source_author>\n" +
                "                    <source_title>source_title9</source_title>\n" +
                "                    <source_page>source_page9</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <text>text2</text>\n" +
                "        </paragraph>\n" +
                "        <paragraph>\n" +
                "            <ref id=\"50\">ref6</ref>\n" +
                "            <ref id=\"50\">ref7</ref>\n" +
                "            <list>\n" +
                "                <item>item12</item>\n" +
                "                <item>item13</item>\n" +
                "            </list>\n" +
                "            <list>\n" +
                "                <item>item14</item>\n" +
                "                <item>item15</item>\n" +
                "            </list>\n" +
                "            <image title=\"title26\" description=\"description22\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title27\" description=\"description23\">ZGVmYXVsdA==</image>\n" +
                "            <link href=\"href10\">\n" +
                "                <image title=\"title28\" description=\"description24\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href11\">\n" +
                "                <image title=\"title29\" description=\"description25\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text10</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author10</source_author>\n" +
                "                    <source_title>source_title10</source_title>\n" +
                "                    <source_page>source_page10</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <quote>\n" +
                "                <quote_text>quote_text11</quote_text>\n" +
                "                <source>\n" +
                "                    <source_author>source_author11</source_author>\n" +
                "                    <source_title>source_title11</source_title>\n" +
                "                    <source_page>source_page11</source_page>\n" +
                "                </source>\n" +
                "            </quote>\n" +
                "            <text>text3</text>\n" +
                "        </paragraph>\n" +
                "        <box title=\"title30\" description=\"description26\">\n" +
                "            <link href=\"href12\">\n" +
                "                <image title=\"title31\" description=\"description27\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href13\">\n" +
                "                <image title=\"title32\" description=\"description28\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <image title=\"title33\" description=\"description29\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title34\" description=\"description30\">ZGVmYXVsdA==</image>\n" +
                "            <textbox>textbox4</textbox>\n" +
                "            <textbox>textbox5</textbox>\n" +
                "        </box>\n" +
                "        <box title=\"title35\" description=\"description31\">\n" +
                "            <link href=\"href14\">\n" +
                "                <image title=\"title36\" description=\"description32\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href15\">\n" +
                "                <image title=\"title37\" description=\"description33\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <image title=\"title38\" description=\"description34\">ZGVmYXVsdA==</image>\n" +
                "            <image title=\"title39\" description=\"description35\">ZGVmYXVsdA==</image>\n" +
                "            <textbox>textbox6</textbox>\n" +
                "            <textbox>textbox7</textbox>\n" +
                "        </box>\n" +
                "        <quote>\n" +
                "            <quote_text>quote_text12</quote_text>\n" +
                "            <source>\n" +
                "                <source_author>source_author12</source_author>\n" +
                "                <source_title>source_title12</source_title>\n" +
                "                <source_page>source_page12</source_page>\n" +
                "            </source>\n" +
                "        </quote>\n" +
                "        <quote>\n" +
                "            <quote_text>quote_text13</quote_text>\n" +
                "            <source>\n" +
                "                <source_author>source_author13</source_author>\n" +
                "                <source_title>source_title13</source_title>\n" +
                "                <source_page>source_page13</source_page>\n" +
                "            </source>\n" +
                "        </quote>\n" +
                "    </section>\n" +
                "    <references>\n" +
                "        <reference id=\"50\">\n" +
                "            <authors>authors0</authors>\n" +
                "            <year>50</year>\n" +
                "            <title>title40</title>\n" +
                "            <pages>pages0</pages>\n" +
                "        </reference>\n" +
                "        <reference id=\"50\">\n" +
                "            <authors>authors1</authors>\n" +
                "            <year>50</year>\n" +
                "            <title>title41</title>\n" +
                "            <pages>pages1</pages>\n" +
                "        </reference>\n" +
                "    </references>\n" +
                "    <corresponding_authors>\n" +
                "        <corresponding_author>corresponding_author0</corresponding_author>\n" +
                "        <corresponding_author>corresponding_author1</corresponding_author>\n" +
                "    </corresponding_authors>\n" +
                "    <citations>\n" +
                "        <citation number=\"50\">\n" +
                "            <authors>authors2</authors>\n" +
                "            <year>50</year>\n" +
                "            <title>title42</title>\n" +
                "            <magazine>magazine0</magazine>\n" +
                "            <link href=\"href16\">\n" +
                "                <image title=\"title43\" description=\"description36\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href17\">\n" +
                "                <image title=\"title44\" description=\"description37\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href18\">\n" +
                "                <image title=\"title45\" description=\"description38\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "        </citation>\n" +
                "        <citation number=\"50\">\n" +
                "            <authors>authors3</authors>\n" +
                "            <year>50</year>\n" +
                "            <title>title46</title>\n" +
                "            <magazine>magazine1</magazine>\n" +
                "            <link href=\"href19\">\n" +
                "                <image title=\"title47\" description=\"description39\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href20\">\n" +
                "                <image title=\"title48\" description=\"description40\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "            <link href=\"href21\">\n" +
                "                <image title=\"title49\" description=\"description41\">ZGVmYXVsdA==</image>\n" +
                "            </link>\n" +
                "        </citation>\n" +
                "    </citations>\n" +
                "</paper>\n");
        scientificPaperRepository.save("/db/sample/library", "paper2.xml", reader);
    }


    @org.junit.Test
    public void testDelete() throws XMLDBException{
        scientificPaperRepository.delete("/db/sample/library", "paper2.xml");
    }
}
