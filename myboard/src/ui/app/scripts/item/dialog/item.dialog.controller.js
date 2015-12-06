var app = angular.module('MyBoard');
app.controller('ItemDialogController', function($scope, taOptions, hotkeys, ItemService, ItemDialogService, ItemDialogShortcuts){

  ItemDialogShortcuts.setup($scope);

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

});
