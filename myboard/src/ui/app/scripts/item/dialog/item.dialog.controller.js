var app = angular.module('MyBoard');
app.controller('ItemDialogController', function($scope, taOptions, hotkeys, ItemService, ItemDialogService){

  $scope.isDialogVisible = function(){
    return ItemDialogService.dialogVisible();
  };

  $scope.dialog = {
    hideDialog: function (){
      ItemDialogService.toggle(false);
    },
    toggleColumn: function (column) {
      var state = column.config.visible;
      column.config.visible = !state;
      console.log('toggleBacklog');
    }
  };

  taOptions.keyMappings.push({
    commandKeyCode: 'esc', testForKey: function(event){
      if(event.keyCode === 27 ){
        $scope.dialog.hideDialog();
        return true;
      }
    }
  });

  hotkeys.bindTo($scope)
    .add({
      combo: 'esc',
      description: 'Closes the item overlay',
      allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
      callback: function () {
        ItemDialogService.toggle(false);
      }
    });

});
