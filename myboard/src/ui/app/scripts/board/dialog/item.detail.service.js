var app = angular.module('MyBoard');
app.factory('ItemDetailService', function(){

  var isDialogVisible = false;

  return {
    isDialogVisible : isDialogVisible
  };

});
