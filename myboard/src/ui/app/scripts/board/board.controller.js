var app = angular.module('MyBoard');

app.controller('BoardController', function ($scope, $window, Items, ItemService, BoardService, ItemDialogService) {


  $scope.toggleColumn = function (column) {
    var state = column.config.visible;
    column.config.visible = !state;
    console.log('toggle col ' + JSON.stringify(column));
  };

  $scope.showDialog = function (col) {
    console.log('show dialog .. col: ' + JSON.stringify(col));
    $scope.item = ItemService.makeNewItem(col);
    ItemDialogService.showDialog();
  };

  Items.query(function (data) {
    console.log('item query: ' + JSON.stringify(data));

    angular.forEach(data, function(value, key) {
      var item = value;
      switch (item.state) {
        case 1:
          BoardService.board().todo.items.push(item);
          break;
        case 2:
          BoardService.board().busy.items.push(item);
          break;
        case 3:
          BoardService.board().done.items.push(item);
          break;
        default:
          break;
      }
    });
  });
  $scope.board = function () {
    return BoardService.board()
  };
});
