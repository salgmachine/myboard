var app = angular.module('MyBoard');
app.factory('ItemDialogService', function(){

  var dialogVisible = false;

  var toggle = function(state){
    dialogVisible = state;
  };

  var isDialogVisible = function(){
    return dialogVisible;
  }

  var showDialog = function(){
    toggle(true);
  };

  return {
    dialogVisible: isDialogVisible,
    toggle: toggle,
    showDialog: showDialog
  }

});
