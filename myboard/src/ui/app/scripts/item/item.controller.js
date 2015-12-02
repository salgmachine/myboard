var app = angular.module('MyBoard');

app.controller('MainController', function($scope, Items){
  $scope.items = Items.query();
  $scope.test = 'asd';
});
