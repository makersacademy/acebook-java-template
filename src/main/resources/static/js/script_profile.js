(function($){
    var $form = $('#likepost');
    $form.on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            url: $form.attr('action'),
            type: 'post',
            data: $form.serialize(),
            success: function(response) {
                    $form.replaceWith(response);
            }
        });
    })
}(jQuery));