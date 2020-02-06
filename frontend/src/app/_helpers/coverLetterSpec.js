export const letterSpec = {
    elements: {
        'paragraphs': {
            menu: [
                {
                    caption: "Add <paragraph>",
                    action: Xonomy.newElementChild,
                    actionParameter: '<paragraph></paragraph>',
                    hideIf: function (jsElement) {
                        return jsElement.parent().name === 'citation';
                    }
                },
            ]
        },
        'paragraph': {
            hasText: true,
            asker: Xonomy.askString,
            menu: [
                {
                    caption: "Delete element",
                    action: Xonomy.deleteElement,
                    hideIf: function (jsElement) {
                        return jsElement.parent().getChildElements("paragraph").length === 1;
                    }
                }
            ]
        },
        'name': {
            hasText: true,
            asker: Xonomy.askString
        },
        'email': {
            hasText: true,
            asker: Xonomy.askString
        },
        'phone_number': {
            hasText: true,
            asker: Xonomy.askString
        },
        'institution': {
            hasText: true,
            asker: Xonomy.askString
        },
        'address': {
            hasText: true,
            asker: Xonomy.askString
        },
        'signature': {
            hasText: true,
            asker: Xonomy.askString
        }
    }
}