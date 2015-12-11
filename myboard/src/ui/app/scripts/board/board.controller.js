var app = angular.module('MyBoard');

app.controller('BoardController', function ($rootScope, $scope, $window, Items, ItemService, BoardService, ItemDialogService) {


  $scope.toggleColumn = function (state) {
    BoardService.toggleColumn(state);
  };

  $scope.showDialog = function (col) {
    if(col.hasOwnProperty('id')){
      $scope.item = col;
    }else{
      $scope.item = ItemService.makeNewItem(col);
    }

    ItemDialogService.showDialog($scope.item);
    console.log('firing item select event');
    $rootScope.$broadcast('item-select');
  };
  $scope.dropSuccessHandler = function ($event, index, state) {
    var column = BoardService.getColumn(state);
    var array = column.column.items;
    array.splice(index, 1);
  };

  $scope.dropFailureHandler = function ($event, index, array) {
    alert(array[index] + ' could be dropped into left list!')
  };

  $scope.onDrop = function ($event, $data, col, index) {
    console.log('ondrop' + JSON.stringify(col));
    var column = BoardService.getColumn(col);
    var array = column.column.items;
    $data.state = column.column.config.state;
    if (index !== undefined) {
      array.splice(index, 0, $data);
    } else {
      array.push($data);
    }
    Items.save($data);
  };

  $scope.getCustomDragElementId = function (index) {
    return 'customDrag' + (index % 2);
  };

  Items.query(function (data) {
    BoardService.addItems(data);
  });

  $scope.boardWidth = function() {
    var count = BoardService.visibleColumns();
    if(count === 0){
      count = 1;
    }

    var parts = 12 / count;
    parts = Math.floor(parts);
    return 'col-xs-' + parts + ' col-sm-' +parts+ ' col-md-' +parts+ ' col-lg-'+parts;
  };

  $scope.board = function () {
    return BoardService.board();
  };

  $scope.getAvailableStates = function () {
    return BoardService.getAvailableStates();
  };
  $scope.getColumn = function(col){
    return BoardService.getColumn(col);
  };

  angular.element(document).ready(function () {
    setupScrollShadow();
  });
});
