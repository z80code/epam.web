;
var entity = {
    FILM: "films",
    SESSION: "sessions"
};

moderator = new function Moderator() {

    Moderator.prototype = cinema;

    var self = this;
    var const_content = '';
    var targetSessionBlock;
    this.run = function () {

        targetSessionBlock = document.getElementById("render_context");
        targetSessionBlock.innerHTML = "";
        var ITEM_MENU_ID_PREFIX = "item_menu_entity_";

        var menu_item_template = document.getElementById("entity_menu_item").innerHTML;
        var menu_template = document.getElementById("entity_menu").innerHTML;
        var menu_items = [];

        var objItem = {};

        for (var item in entity) {
            objItem.menu_item_id = ITEM_MENU_ID_PREFIX + item;
            objItem.methodOnClick = "moderator.getEntity(entity." + item + ", this); return false;";
            objItem.name = item;
            menu_items.push(Mustache.render(menu_item_template, objItem));
        }
        menu_items.items = menu_items.join();
        const_content = Mustache.render(menu_template, menu_items);
        const_content += document.getElementById("table_container_temp").innerHTML;
        targetSessionBlock.innerHTML = const_content;
        table_container = document.getElementById("table_container");
        table_entity_head_temp = document.getElementById("table_entity_head").innerHTML;
    };

    this.getEntity = function (entity_item, senderId) {
        var size = 10;
        var requestUrl = '/api/moderator/' + entity_item.toLowerCase() + '/';
        table_entity_template = document.getElementById("table_entity_" + entity_item.toLowerCase()).innerHTML;
        ajax.get(requestUrl, function (results) {
            var resultsRaw = JSON.parse(results);
            var table_entity_items_count = +resultsRaw.data;

            if (resultsRaw.status == "OK") {
                $('#pagination').twbsPagination('destroy');
                $(function () {
                    window.pagObj = $('#pagination').twbsPagination({
                        totalPages: table_entity_items_count / size + ((table_entity_items_count % size) != 0 ? 1 : 0),
                        visiblePages: 5,
                        initiateStartPageClick: true,
                        onPageClick: function (event, page) {
                            updateTable(resultsRaw, entity_item, requestUrl, (page - 1) * 10, size);
                        }
                    })
                });
                //updateTable(resultsRaw, entity_item, requestUrl, 0, 10);
            } else {
                // TODO error handler
            }
        });
        //
    };

    var table_container;
    var table_entity_head_temp;
    var table_entity_template;

    function updateTable(resultsRaw, entity_item, requestUrl, from, size) {
        ajax.get(requestUrl + from + ';' + size, function (results) {
            table_container.innerHTML = "";
            resultsRaw = JSON.parse(results, function (key, value) {
                if (key == 'date') return new Date(value);
                return value;
            });
            if (resultsRaw.status == "OK") {

                var name = entity_item;
                if (resultsRaw.data.length > 0) {
                    var fields = [];
                    for (var field in resultsRaw.data[0]) {
                        fields.push(field);
                    }

                    var head = Mustache.render(table_entity_head_temp, {fields: fields});

                    table_container.innerHTML += Mustache.render(table_entity_template, {
                        items: resultsRaw.data,
                        name: name,
                        head: head
                    });

                } else {
                    // TODO with empty array of result
                }

            } else {
                // TODO error handler
            }
        });
    }

    this.edit = function (itemId) {
        console.log("edit for " + itemId);
    }
    this.delete = function (itemId) {
        console.log("delete for " + itemId);
    }
};


