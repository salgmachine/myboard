var app = angular.module('MyBoard');
app.factory('BoardService', function () {

  var boardObj = {
    name: '',
    owner: '',
    columns: [
      {
        column: {
          title: 'board.columns.backlog',
          showWith: [20],
          config: {
            visible: false,
            state: 10
          }
          ,
          items: []
        }
      }
      ,
      {
        column: {
          title: 'board.columns.todo',
          config: {
            visible: true,
            maxItems: 1,
            maxEffort: 20,
            state: 20
          }
          ,
          items: []
        }
      },
      {
        column: {
          title: 'board.columns.busy',
          config: {
            visible: true,
            maxItems: 1,
            maxEffort: 20,
            state: 30
          }
          ,
          items: []
        }
      }
      ,
      {
        column: {
          title: 'board.columns.done' ,

          config: {
            visible: true,
            maxItems: 1,
            maxEffort: 20,
            state: 40
          }
          ,
          items: []
        }
      }
      ,
      {
        column: {
          title: 'board.columns.release' ,
          showWith: [40],
          config: {
            visible: false,
            state: 50
          }
          ,
          items: []
        }
      }]
  };

  var board = function () {
    return boardObj;
  };
  var addItems = function (list) {
    angular.forEach(list, function(e){
      angular.forEach(boardObj.columns, function(c){
        console.log('adding item ' + JSON.stringify(e) + " to col " + JSON.stringify(c));
        if(e && e.state === c.column.config.state){
          c.column.items.push(e);
          console.log('pushing item ' + e.id + " with state " + e.state + " tocol " + JSON.stringify(c));
        }
      })
    })
  };
  var getAvailableStates = function(){
    var states = [];
    angular.forEach(boardObj.columns, function(col){
      states.push(col.column.config.state);
    });
    return states;
  };
  var toggleForState = function(state) {

    var column = getColumn(state).column;
    column.config.visible = !column.config.visible;

    if(column.config.visible && column.showWith && column.showWith.length >= 1) {
      if(column.showWith && column.showWith.length >= 1) {
        angular.forEach(column.showWith, function (s){
          getColumn(s).column.config.visible = true;
        });
        angular.forEach(getAvailableStates(), function (s){
          if(s !== state && column.showWith.indexOf(s) < 0){
            getColumn(s).column.config.visible = false;
          }
        });
      }

    }


  };
  var getColumn = function (forState) {

    var obj = null;
    angular.forEach(boardObj.columns, function(col){
      if(col.column.config.state === forState) {
        obj = col;
      }
    });
    return obj;
  };

  var visibleColumns = function(){
    var count = 0;

    angular.forEach(getAvailableStates(), function(s){
      var column = getColumn(s).column;
      if(column.config.visible) {
        count++;
      }
    });

    return count;
  };

  return {
    board: board,
    addItems: addItems,
    getAvailableStates: getAvailableStates,
    getColumn: getColumn,
    toggleColumn: toggleForState,
    visibleColumns: visibleColumns
  };
});
