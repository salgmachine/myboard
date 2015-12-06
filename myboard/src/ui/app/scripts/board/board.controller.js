var app = angular.module('MyBoard');

app.controller('BoardController', function ($scope, Items, ItemService, BoardService, ItemDialogService) {
  $scope.board = BoardService.board;

  $scope.toggleColumn = function (column) {
    var state = column.config.visible;
    column.config.visible = !state;
    console.log('toggle col ' + JSON.stringify(column));
  };

  $scope.showDialog = function(col) {
    console.log('show dialog .. ');
    $scope.item = ItemService.makeNewItem();
    ItemDialogService.showDialog(col, $scope.item);
  };

  var items = Items.query(function () {
    for (item in items) {
      switch (item.state) {
        case 1:
          $scope.board.todo.items.push(item);
          break;
        case 2:
          $scope.board.busy.items.push(item);
          break;
        case 3:
          $scope.board.done.items.push(item);
          break;
        default:
          break;
      }
    }
  });
});
