var app = angular.module('MyBoard');
app.controller('ItemDialogController', function ($http, $scope, taOptions, hotkeys, Items, ItemService, ItemDialogService, ItemDialogShortcuts, BoardService) {

  ItemDialogShortcuts.setup($scope);

  $scope.isDialogVisible = function () {
    return ItemDialogService.dialogVisible();
  };

  $scope.dialog = {
    hideDialog: function () {
      ItemDialogService.toggle(false);
    }
  };

  $scope.onSave = function () {
    var newItemToStore = $scope.$parent.item;
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
