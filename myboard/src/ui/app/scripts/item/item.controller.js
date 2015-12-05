var app = angular.module('MyBoard');

app.controller('MainController', function ($scope, Items, ItemService, BoardService, ItemDetailService, hotkeys) {
  $scope.board = BoardService.board;

  $scope.dialog = {
    showDialog: function (mode) {
      $scope.isDialogVisible = true;
      var item = ItemService.makeNewItem();
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
      $scope.item = item;

    },
    hideDialog: function (){
      $scope.isDialogVisible = false;
    }
  };

  hotkeys.bindTo($scope)
    .add({
      combo: 'esc',
      description: 'Closes the item overlay',
      callback: function () {
        $scope.isDialogVisible = false;
      }
    });

  $scope.toggle = function (column) {
    var state = column.config.visible;
    column.config.visible = !state;
    console.log('toggleBacklog');
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
  $scope.test = 'asd';
});
