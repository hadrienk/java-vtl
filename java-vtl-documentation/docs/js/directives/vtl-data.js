/*global define*/
'use strict';

define(['angular',], function (angular) {
    var moduleName = 'vtl.data';
    angular.module(moduleName, [])
        .directive('vtlData', function () {
            return {
                restrict: 'E',
                scope: {
                    datasets: '=',
                },
                transclude: true,
                replace: true,
                templateUrl: '../../js/directives/vtl-data.html',
                // controller: ['$scope', function VtlDataController($scope) {
                //     $scope.input = [];
                //
                //     $scope.addDataset = function (dataset) {
                //         $scope.input.push(dataset);
                //     }
                //
                // }],
                // //controllerAs: 'vtlData',
                // link: function (scope, element, attrs) {
                //     console.log(scope);
                // }
            };
        }).directive('vtlDataTransclude', function () {
        return {
            restrict: 'A',
            require: '^vtlData',
            link: function (scope, elm, attrs) {
                var tab = scope.$eval(attrs.uibTabContentTransclude).tab;

                //Now our tab is ready to be transcluded: both the tab heading area
                //and the tab content area are loaded.  Transclude 'em both.
                tab.$transcludeFn(tab.$parent, function (contents) {
                    angular.forEach(contents, function (node) {
                        if (isTabHeading(node)) {
                            //Let tabHeadingTransclude know.
                            tab.headingElement = node;
                        } else {
                            elm.append(node);
                        }
                    });
                });
            }
        };
    });

    return moduleName;
});
