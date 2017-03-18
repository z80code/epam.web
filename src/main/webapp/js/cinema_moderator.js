;
moderator = new function Moderator() {

    Moderator.prototype = cinema.prototype;

    var entity = {
        FILM: "films",
        SESSION: "session"
    };

    var self = this;

    this.run = function () {
        self.targetSessionBlock = document.getElementById("render_context");
        console.log("ok");
        //Cinema.getFilms.call(self, selectionType.ALL);
    };



};



