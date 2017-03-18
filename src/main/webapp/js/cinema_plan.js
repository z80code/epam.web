;var plan = (function () {

    function Plan() {
        var seatStatus = {
            FREE: "free",
            RESERVED: "reserved",
            SOLD: "sold",
            SELECTED: "selected"
        }

        var selectPlaces = new SelectPlaceHolder();
        var theaterPlaces;
        var sessionId = cookies.getCookie("sessionId");
        var booking_template = '';
        this.run = function () {

            if (!sessionId) document.location = "../";
            var SEAT_BOOKING_TEMPLATE = "../templates/seat_booking.html";
            ajax.get(SEAT_BOOKING_TEMPLATE, function (booking_template_req_result) {
                booking_template = booking_template_req_result;
                ajax.get(api.RESERVE + sessionId, function (sessionRequestResult) {

                    var sessionRequestObject = JSON.parse(sessionRequestResult);

                    if (sessionRequestObject.status == "OK") {

                        updatePlaces(sessionRequestObject.data, booking_template)
                    }
                });
            });
        };

        function updatePlaces(viewTemplateModel, booking_template) {

            theaterPlaces = viewTemplateModel.placeMap;

            var render_context = document.getElementById('render_context');

            viewTemplateModel.theater.seatNumber = theaterPlaces.length;

            viewTemplateModel.theater.seatSold = theaterPlaces.filter(function (item) {
                return item.state == seatStatus.SOLD;
            }).length;
            viewTemplateModel.theater.seatReserved = theaterPlaces.filter(function (item) {
                return item.state == seatStatus.RESERVED;
            }).length;
            viewTemplateModel.theater.seatFree = theaterPlaces.filter(function (item) {
                return item.state == seatStatus.FREE;
            }).length;
            render_context.innerHTML = Mustache.render(booking_template, viewTemplateModel);

            var userBookingIds = viewTemplateModel.userBookingIds;
            // here adding seats reserved by current user

            userBookingIds.forEach(function (seat) {
                var currentTheaterSeat = theaterPlaces.find(function (theaterSeat) {
                    return theaterSeat.id == seat;
                });
                currentTheaterSeat.state = seatStatus.SELECTED;
                selectPlaces.add(currentTheaterSeat);
            });
            costCalc();
            var planRoot = document.getElementById("plan");

            theaterPlaces.forEach(function (obj) {
                // render seat
                planRoot.appendChild(createSeat(obj).mainTag);
            });
        }


        this.sendReserve = function () {
            ajax.put(api.RESERVE + sessionId, function (sessionRequestResult) {
                var sessionRequestObject = JSON.parse(sessionRequestResult);

                if (sessionRequestObject.status == "OK") {
                    selectPlaces.clear();
                    updatePlaces(sessionRequestObject.data, booking_template);
                }
            }, selectPlaces.getArray());
        };

        // event seat click
        this.seatClick = function (id) {
            var item = theaterPlaces.find((function (item) {
                return item.id == id;
            }));

            var stateSeat = seatStatus.FREE;

            if (item.state == seatStatus.RESERVED || item.state == seatStatus.SOLD) {
                return;
            }

            if (selectPlaces.contains(item)) {
                selectPlaces.deletePlace(item);
            } else {
                selectPlaces.add(item);
                stateSeat = seatStatus.SELECTED;
            }

            document.getElementById(item.id).setAttribute("class", "seat_status_" + stateSeat);
            // total cost calc
            costCalc();
        };
// end
        function costCalc() {

            var seatsView = document.getElementById("list_of_seats");
            var total_cost = document.getElementById("total_cost");

            if (selectPlaces.getLength() > 0) {
                seatsView.innerHTML = "";
                selectPlaces.getArray().forEach(
                    function (seat) {
                        seatsView.innerHTML +=
                            '<span class="seat_wraper">' +
                            '<span class="seat">' +
                            '<span class="col">' +
                            '<span class="number">' + seat.row + '</span>' +
                            '<span class="txt">row, ' +
                            '</span>' +
                            '</span>' +
                            '<span class="col">' +
                            '<span class="number">' + seat.seat + '</span>' +
                            '<span class="txt">seat</span>' +
                            '</span>' +
                            '<span class="col">' +
                            '<span class="cost">' + parseInt(seat.cost / 100) + "." + (seat.cost % 100) + '</span>' +
                            '<span class="txt">byr</span>' +
                            '</span>' +
                            '</span>'
                        ;
                    });

                total_cost.innerHTML = "";
                var sum = 0;
                selectPlaces.getArray().forEach(
                    function (seat) {
                        sum += +seat.cost;
                    });
                total_cost.innerHTML = "BYR " + (sum / 100).toFixed(2);
            } else {
                seatsView.innerHTML = "Not selected.";
                total_cost.innerHTML = "BYR 0.00";
            }
        }

        /*seat create*/
        function createSeat(seatData) {
            var popUpInfo_innerHTML = "id: " + seatData.id + "\n" +
                "Row: " + (seatData.row || "0") + " " +
                "Seat: " + (seatData.seat || "0") + "\n" +
                "Cost: " + ((parseInt(seatData.cost / 100) + "." + (seatData.cost % 100) ) || "0") + " BYR";

            return new Element("div", {
                "class": "seat_status_" + seatData.state,
                "id": seatData.id,
                "onclick": "plan.seatClick(this.id)",
                "style": "left: " + seatData.x + "px; top: " + seatData.y + "px;"
            }).addChild(new Element("div", {"class": "popup_info"}, popUpInfo_innerHTML));
        }

        /* end */

        /* my collection for holding selected seats */
        function SelectPlaceHolder() {
            var selectedPlaces = [];
            var next = 0;

            this.getLength = function () {
                return selectedPlaces.length;
            };

            this.add = function (place) {
                if(!selectedPlaces.find(function (item) {
                        item.id==place.id;
                    })) {
                    selectedPlaces.push(place);
                }

            };

            this.getArray = function () {
                return selectedPlaces;
            };

            this.contains = function (place) {
                return ~selectedPlaces.indexOf(place);
            };

            this.deletePlace = function (place) {
                selectedPlaces.splice(selectedPlaces.indexOf(place), 1);
            }

            this.clear = function () {
                selectedPlaces = [];
            }
        }

        /* end */
    }

    return new Plan();
})();

