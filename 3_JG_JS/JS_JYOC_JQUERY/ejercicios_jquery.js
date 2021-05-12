$(inicio);

function inicio() {


    $(document).ready(function() {
        $("button").click(function() {
            $("#test").hide();
        });
    });

    $("p").click(function() {
        // acciones a realizar 
    });

    $("p").click(acciones);

    function acciones() {
        // acciones a realizar 
    };

    // $("p").click(function() {
    //     $(this).hide();
    // });


    // $('#cosa').hover(function() {
    //         alert("entraste");
    //     },
    //     function() {
    //         alert("saliste");
    //     });

    $('#cosa').hover(function() {
            $(this).hide();
        },
        function() {
            $(this).show();
        });


    $("h1").on({
        mouseenter: function() { $(this).css("background-color", "lightgray"); },
        mouseleave: function() { $(this).css("background-color", "lightblue"); },
        click: function() { $(this).css("background-color", "yellow"); }
    });





}