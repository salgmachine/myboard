var app = angular.module('MyBoard');

app.controller('BoardController', function ($rootScope,
                                            $scope,
                                            $window,
                                            $element,
                                            Storage,
                                            Items,
                                            Boards,
                                            ItemService,
                                            BoardService,
                                            ItemDialogService) {


  $scope.onDropComplete = function($data,$event, targetState) {

    console.log('drag complete with data ' + JSON.stringify($data) );
    console.log('drag complete with event ' + JSON.stringify($event) );
    console.log('drag complete with target state ' + JSON.stringify(targetState) );

    var itm = $data;
    var id = itm.id;

    if(itm.state === targetState){
      // do sth useful
    }else{
      moveItemToState($data, targetState);
    }



    var draggeddiv = document.getElementById("item-panel-" + id);
    $(draggeddiv).css('opacity', '1');
  };

  $scope.$on('draggable:start', function($data,$event) {
    console.log('drag start data ', $data);
    console.log('drag start evt ', $event);
    $scope.draggedItem = $event.data;
    var div = $('#clone-helper .item-panel');
    var draggeddiv = document.getElementById("item-panel-" + $event.data.id);
    $(div).css('width', draggeddiv.clientWidth + 'px');
    $(div).css('height', draggeddiv.clientHeight + 'px');
    $(draggeddiv).css('opacity', '0');
  });

  $scope.draggedItem = null;



  $scope.toggleColumn = function (state) {
    BoardService.toggleColumn(state);
  };

  $scope.showColumnDlg = function (state) {
    var className = '.board-column.board-column-' + state + ' div.board-column-title.shadowdiv';
    console.log('showColDlg ' + className + " height " + $(className).css('height'));

    if ($(className).css('height') === '200px') {
      $(className).css('height', '25px');
      $(className).removeClass('show');
      $(className).addClass('noshow');
    } else {
      $(className).css('height', '200px');
      $(className).addClass('show');
      $(className).removeClass('noshow');
    }

  };

  $scope.showDialog = function (col) {
    if (col.hasOwnProperty('id')) {
      $scope.item = col;
    } else {
      $scope.item = ItemService.makeNewItem(col);
    }

    ItemDialogService.showDialog($scope.item);
    console.log('firing item select event');
    $rootScope.$broadcast('item-select');
  };

  $scope.boardWidth = function () {
    var count = BoardService.visibleColumns();
    if (count === 0) {
      count = 1;
    }

    var parts = 12 / count;
    parts = Math.floor(parts);
    return 'col-xs-' + parts + ' col-sm-' + parts + ' col-md-' + parts + ' col-lg-' + parts;
  };

  $scope.board = function () {
    return BoardService.board();
  };

  $scope.getAvailableStates = function () {
    var states = BoardService.getAvailableStates();
    console.log('returning states' + JSON.stringify(states));
    return states;
  };

  $scope.getColumn = function (col) {
    return BoardService.getColumn(col);
  };

  $scope.decrementState = function(itm){
    var newState = BoardService.getAvailableStates(itm.state);
    if(newState.length > 1) {
      var index = newState.length-1;
      newState = newState[index];
      moveItemToState(itm, newState);
    }else{
      itm.state = itm.state;
    }
    itm.showMenu = false;
  };

  $scope.incrementState = function(itm){

    var last = 0;
    var newState = -1;
    for(var x in BoardService.getAvailableStates()) {
      var array = BoardService.getAvailableStates();
      if(last === 0 && array[x] > itm.state){
        newState = array[x];
        break;
      }
    }
    // explicit check
    if(newState === -1) {
      itm.state = itm.state;
    }else {
      moveItemToState(itm, newState);
    }
    itm.showMenu = false;
  };

  function moveItemToState (itm, newState){

    if(newState !== itm.state) {
      // remove from source column
      var idx = BoardService.getColumn(itm.state).column.items.indexOf(itm);
      if(idx > -1) {
        BoardService.getColumn(itm.state).column.items.splice(idx, 1);
      }
      // add to target column
      itm.state = newState;
      BoardService.getColumn(newState).column.items.push(itm);

      // save changed item
      Items.save(itm);
    }

  }

  Items.query(function (data) {
    BoardService.addItems(data);
  });

  /*Boards.query(function (data) {
    var boards = data;
    if (boards.length === 0) {
      console.log('found no board ..');
      var newBoard = BoardService.board();

    }
    console.log('data for board query: ' + JSON.stringify(data));
  });

  if (Storage.get('user') === null) {
    console.log('no user in localstorage found...');
  } else {

  }*/

  angular.element(document).ready(function () {
    setupScrollShadow();
  });
});
