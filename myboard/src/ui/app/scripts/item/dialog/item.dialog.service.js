var app = angular.module('MyBoard');
app.factory('ItemDialogService', function(){

  var dialogVisible = false;

  var itm;

  var getSelectedItem = function(){
    return itm;
  };

  var toggle = function(state){
    dialogVisible = state;
  };

  var isDialogVisible = function(){
    return dialogVisible;
  };

  var showDialog = function(item){
    itm = item;
    toggle(true);
  };

  return {
    dialogVisible: isDialogVisible,
    toggle: toggle,
    showDialog: showDialog,
    getSelectedItem: getSelectedItem
  }

});
