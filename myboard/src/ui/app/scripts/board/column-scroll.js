
function setupScrollShadow() {
  var  tb = $('.board-column-title.shadowdiv');
  var tbs = "shadow";

  $('.board-column-list-wrapper').scroll(function() {
    if($(this).scrollTop()) {
      var parent = $(this).closest('.board-column').find('.board-column-title');
      console.log('parent: ' + JSON.stringify(parent));
      parent.addClass(tbs);
    } else {
      var parent = $(this).closest('.board-column').find('.board-column-title');
      console.log('parent: ' + JSON.stringify(parent));
      parent.removeClass(tbs);
    }
  });

}
