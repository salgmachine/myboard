var app = angular.module('MyBoard');
app.controller('ItemDialogController', function ($rootScope, $http, $scope, taOptions, hotkeys, Items, ItemService, ItemDialogService, ItemDialogShortcuts, BoardService) {

  ItemDialogShortcuts.setup($scope);

  $scope.isDialogVisible = function () {
    return ItemDialogService.dialogVisible();
  };

  $scope.dialog = {
    hideDialog: function () {
      ItemDialogService.toggle(false);
    }
  };

  $scope.selectedItem = {};

  $rootScope.$on('item-select', function (){
    console.log('item select event caught');
    $scope.selectedItem  = ItemDialogService.getSelectedItem();
    console.log('item select event caught - item ' + JSON.stringify($scope.selectedItem));
  });


  $scope.onSave = function () {
    var newItemToStore = ItemDialogService.getSelectedItem();
    Items.save(newItemToStore, function(data) {
      if(data) {
        data.drag = true;
        BoardService.getColumn(newItemToStore.state).column.items.push(data);
        /*BoardService.board().todo.items.push(data);*/
        $scope.dialog.hideDialog();
      }
    }, function(data, headers) {
      console.log("error data : " + JSON.stringify( data));
      console.log("error headera : " + JSON.stringify( headers));
    });
  };
});
