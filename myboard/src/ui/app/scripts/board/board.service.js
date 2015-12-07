var app = angular.module('MyBoard');
app.factory('BoardService', function () {

  var boardObj = {
    backlog: {
      config: {
        visible: false
      }
      ,
      items: []
    }
    ,
    todo: {
      config: {
        visible: true,
        maxItems: 1,
        maxEffort: 20
      }
      ,
      items: []
    }
    ,
    busy: {
      config: {
        visible: true,
        maxItems: 1,
        maxEffort: 20
      }
      ,
      items: []
    }
    ,
    done: {
      config: {
        visible: true,
        maxItems: 1,
        maxEffort: 20
      }
      ,
      items: []
    }
    ,
    release: {
      config: {
        visible: false
      }
      ,
      items: []
    }
  };

  var board = function () {
      return boardObj;
  };
  return {
    board: board
  };
});
