var app = angular.module('MyBoard');

app.controller('MainController', function ($scope, Items, ItemService) {
  $scope.board = ItemService.board;

  $scope.toggleBacklog = function () {
    var state = $scope.board.backlog.config.visible;
    $scope.board.backlog.config.visible = !state;
    console.log('toggleBacklog');
  };

  $scope.toggleRelease = function () {
    var state = $scope.board.release.config.visible;
    $scope.board.release.config.visible = !state;
    console.log('toggleRelease');
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
