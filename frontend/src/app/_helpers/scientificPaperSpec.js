export const paperDocSpec = {
    elements: {
        'paper': {
            menu: [
                {
                    caption: "Add <quote>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<quote about="" rel="pred:referenceOf" href=""><quote_text></quote_text><source></source></quote>',
                },
                {
                    caption: "Add <section>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<section title=""><paragraph><text></text><annotations></annotations></paragraph></section>',
                }

            ],
            attributes: {
                "date_received": {
                    asker: Xonomy.askString,
                },
                "date_revised": {
                    asker: Xonomy.askString,
                },
                "date_accepted": {
                    asker: Xonomy.askString,
                },
                "xsi:noNamespaceSchemaLocation": {
                    asker: Xonomy.askString,
                }
            }
        },
        'status': {
            mustBeBefore: ['paper_title', 'authors', 'abstract', 'quote', 'section', 'references', 'corresponding_authors', 'citations']
        },
        'waiting': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'published': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'archived': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'paper_title': {
            hasText: true,
            asker: Xonomy.askString,
            mustBeAfter: ['status'],
            mustBeBefore: ['authors', 'abstract', 'quote', 'section', 'references', 'corresponding_authors', 'citations'],
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'authors': {
            hasText: true,
            asker: Xonomy.askString,
            mustBeAfter: ['status', 'paper_title'],
            mustBeBefore: ['abstract', 'quote', 'section', 'references', 'corresponding_authors', 'citations'],
            menu: [
                {
                    caption: "Add <author>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<author about=""><name property="pred:name"></name><university property="pred:works"></university><city property="pred:city"></city><state property="pred:state"></state><country property="pred:country"></country></author>',
                    hideIf: function (jsElement) {
                        return jsElement.parent().name === 'citation';
                    }
                },
            ]
        },
        'author': {
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("author").length === 1;
                    }
                }
            ],
            attributes: {
                "about": {
                    asker: Xonomy.askString,
                }
            }
        },
        'name': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'university': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'city': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'state': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'country': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'abstract': {
            mustBeAfter: ['status', 'paper_title', 'authors'],
            mustBeBefore: ['quote', 'section', 'references', 'corresponding_authors', 'citations'],
            menu: [
                {
                    caption: "Add <abstract_element>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<abstract_element title=""></abstract_element>',
                }
            ]
        },
        'keywords': {
            mustBeBefore: ['abstract_element'],
            menu: [
                {
                    caption: "Add <keyword>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<keyword property="pred:keyword"></keyword>',
                }
            ]
        },
        'keyword': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("keyword").length === 1;
                    }
                }
            ],
            attributes: {
                "property": {
                    asker: Xonomy.askString,
                }
            }
        },
        'abstract_element': {
            hasText: true,
            asker: Xonomy.askString,
            mustBeAfter: ['keywords'],
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("abstract_element").length === 1;
                    }
                }
            ],
            attributes: {
                "title": {
                    asker: Xonomy.askString,
                }
            }
        },
        'quote': {
            mustBeAfter: ['status', 'paper_title', 'authors', 'abstract'],
            mustBeBefore: ['references', 'corresponding_authors', 'citations'],
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ],
            attributes: {
                "about": {
                    asker: Xonomy.askString,
                },
                "rel": {
                    asker: Xonomy.askString,
                },
                "href": {
                    asker: Xonomy.askString,
                },
            }
        },
        'quote_text': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'source': {
            menu: [
                {
                    caption: "Add <source_author>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<source_author></source_author>',
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("source_author").length === 1;
                    }
                },
                {
                    caption: "Add <source_title>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<source_title></source_title>',
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("source_title").length === 1;
                    }
                },
                {
                    caption: "Add <source_page>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<source_page></source_page>',
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("source_page").length === 1;
                    }
                },
            ]
        },
        'source_author': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ]
        },
        'source_title': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ]
        },
        'source_page': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ]
        },
        'section': {
            mustBeAfter: ['status', 'paper_title', 'authors', 'abstract'],
            mustBeBefore: ['references', 'corresponding_authors', 'citations'],
            menu: [
                {
                    caption: "Add <section>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<section title=""><paragraph><text></text><annotations></annotations></paragraph></section>',
                },
                {
                    caption: "Add <paragraph>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<paragraph><text></text><annotations></annotation></paragraph>',
                },
                {
                    caption: "Add <box>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<box title="" description=""><image title="" description=""></image><textbox></textbox></box>',
                },
                {
                    caption: "Add <quote>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<quote about="" rel="pred:referenceOf" href=""><quote_text></quote_text><source></source></quote>',
                },
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return ((jsElement.parent().getChildElements("section").length === 1) && (jsElement.parent().name === 'paper'));
                    }
                }
            ],
            attributes: {
                "title": {
                    asker: Xonomy.askString,
                },
            }
        },
        'paragraph': {
            menu: [
                {
                    caption: "Add <ref>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<ref id=""></ref>',
                },
                {
                    caption: "Add <list>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<list><item></item></list>',
                },
                {
                    caption: "Add <image>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<image title="" description=""></image>',
                },
                {
                    caption: "Add <link>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<link href=""></link>',
                },
                {
                    caption: "Add <quote>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<quote about="" rel="pred:referenceOf" href=""><quote_text></quote_text><source></source></quote>',
                },
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("paragraph").length === 1;
                    }
                }
            ]
        },
        'ref': {
            hasText: true,
            asker: Xonomy.askString,
            attributes: {
                "id": {
                    asker: Xonomy.askString,
                }
            }
        },
        'list': {
            menu: [
                {
                    caption: "Add <item>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<item></item>',
                },
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ]
        },
        'item': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("item").length === 1;
                    }
                }
            ]
        },
        'image': {
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().name === 'box';
                    }
                }
            ],
            attributes: {
                "title": {
                    asker: Xonomy.askString,
                },
                "description": {
                    asker: Xonomy.askString,
                }
            }
        },
        'link': {
            menu: [
                {
                    caption: "Add <image>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<image title="" description=""></image>',
                    hideIf: function (jsElement) {
                        return ((jsElement.parent().getChildElements("image").length === 1) || (jsElement.parent().getChildElements("text").length === 1));
                    }
                },
                {
                    caption: "Add <text>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<text></text>',
                    hideIf: function (jsElement) {
                        return ((jsElement.parent().getChildElements("image").length === 1) || (jsElement.parent().getChildElements("text").length === 1));
                    }
                },
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().name === 'citation';
                    }
                }
            ],
            attributes: {
                "href": {
                    asker: Xonomy.askString,
                },
            }
        },
        'text': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'annotations': {
            menu: [
                {
                    caption: "Add <annotation>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<annotation></annotation>',
                }
            ]
        },
        'annotation': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ]
        },
        'box': {
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                }
            ],
            attributes: {
                "title": {
                    asker: Xonomy.askString,
                },
                "description": {
                    asker: Xonomy.askString,
                },
            }
        },
        'textbox': {
            hasText: true,
            asker: Xonomy.askString
        },
        'references': {
            mustBeAfter: ['status', 'paper_title', 'authors', 'abstract', 'quote', 'section'],
            mustBeBefore: ['corresponding_authors', 'citations'],
            menu: [
                {
                    caption: "Add <reference>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<reference id=""><authors></authors><year></year><title></title><pages></pages></reference>'
                }
            ]
        },
        'reference': {
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("reference").length === 1;
                    }
                }
            ],
            attributes: {
                "id": {
                    asker: Xonomy.askString,
                }
            }
        },
        'year': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'title': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'pages': {
            hasText: true,
            asker: Xonomy.askString,
        },
        'corresponding_authors': {
            mustBeAfter: ['status', 'paper_title', 'authors', 'abstract', 'quote', 'section', 'references'],
            mustBeBefore: ['citations'],
            menu: [
                {
                    caption: "Add <corresponding_author>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<corresponding_author></corresponding_author>'
                }
            ]
        },
        'corresponding_author': {
            oneliner: true,
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("corresponding_author").length === 1;
                    }
                }
            ]
        },
        'citations': {
            mustBeAfter: ['status', 'paper_title', 'authors', 'abstract', 'quote', 'section', 'references', 'corresponding_authors'],
            menu: [
                {
                    caption: "Add <citation>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<citation number=""><authors></authors><year></year><title></title><magazine></magazine><link href=""></link></citation>'
                }
            ]
        },
        'citation': {
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("citation").length === 1;
                    }
                }
            ],
            attributes: {
                "number": {
                    asker: Xonomy.askString,
                }
            }
        },
        'magazine': {
            hasText: true,
            asker: Xonomy.askString,
        },
    }
}