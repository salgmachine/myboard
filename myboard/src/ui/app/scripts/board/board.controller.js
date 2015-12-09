var app = angular.module('MyBoard');

app.controller('BoardController', function ($scope, $window, Items, ItemService, BoardService, ItemDialogService) {


  $scope.toggleColumn = function (column) {
    var state = column.config.visible;
    column.config.visible = !state;
    console.log('toggle col ' + JSON.stringify(column));
  };

  $scope.showDialog = function (col) {
    if(col.hasOwnProperty('id')) {
      $scope.item = col;
    }else {
      $scope.item = ItemService.makeNewItem(col);
    }

    ItemDialogService.showDialog();
  };
  $scope.dropSuccessHandler = function($event, index, array) {
    array.splice(index, 1);
  };

  $scope.dropFailureHandler = function($event, index, array) {
    alert(array[index] + ' could be dropped into left list!')
  };

  $scope.onDrop = function($event, $data, array, index) {
    if (index !== undefined) {
      array.splice(index, 0, $data);
    } else {
      array.push($data);
    }
  };

  $scope.getCustomDragElementId = function (index) {
    return 'customDrag' + (index % 2);
  }

  Items.query(function (data) {
    console.log('item query: ' + JSON.stringify(data));

    angular.forEach(data, function(value, key) {
      var item = value;
      item.drag = true;
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
