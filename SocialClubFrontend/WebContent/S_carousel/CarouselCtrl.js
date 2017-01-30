angular.module('myApp', ['ui.bootstrap']);
function CarouselDemoCtrl($scope){
  $scope.myInterval = 3000;
  $scope.slides = [
    {
      image: 'http://unsplash.it/680/380?random=4'
    },
    {
      image: 'http://unsplash.it/680/380?random=4'
    },
    {
      image: 'http://unsplash.it/680/380?random=4'
    },
    {
      image: 'http://unsplash.it/680/380?random=4'
    }
  ];
}