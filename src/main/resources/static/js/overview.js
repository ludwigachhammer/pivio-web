$(function () {
    $('#searchbox').keyup(function () {
        var searchTerm = $(this).val().toLowerCase();
        $(`.card[data-meta*='${searchTerm}']`).show();
        $('.card').not(`[data-meta*='${searchTerm}']`).hide();

        if (searchTerm.trim() == '') {
            $(".card").show();
        }
        countArtifacts();
    });
    
    $('#searchbox').change(function () {
    	console.log("Text changed");
        var searchTerm = $(this).val().toLowerCase();
        $(`.card[data-meta*='${searchTerm}']`).show();
        $('.card').not(`[data-meta*='${searchTerm}']`).hide();

        if (searchTerm.trim() == '') {
            $(".card").show();
        }
        countArtifacts();
    });

    countArtifacts();
});


//TODO: Hide and show matching artifacts when hierarchical tree


function countArtifacts() {
    var n = $('a.ui.card:not([style*="display: none"])').length;
    $("#artifactsheader").text("Application/Services (" + n + ")");
};
