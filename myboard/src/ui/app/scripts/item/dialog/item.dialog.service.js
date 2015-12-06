var app = angular.module('MyBoard');
app.factory('ItemDialogService', function(){

  var dialogVisible = false;

  var toggle = function(state){
    dialogVisible = state;
  };

  var isDialogVisible = function(){
    return dialogVisible;
  }

  var showDialog = function(mode, item){
    toggle(true);
    switch (mode) {
      case 'backlog':
        item.state = 0;
        break;
      case 'todo':
        item.state = 1;
        break;
      case 'busy':
        item.state = 2;
      default:
        break;
    }
  };

  return {
    dialogVisible: isDialogVisible,
    toggle: toggle,
    showDialog: showDialog
  }

});
