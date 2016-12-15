(function () {
    var app = angular.module("couponSystem");

    var companyExpenses = function ($scope, $http, $uibModalInstance, uiGridConstants, companyName) {

    	$scope.companyName = companyName;

    	$scope.expenses = {
    			
    			showGridFooter: true,
    			showColumnFooter: true,
    			enableRowSelection: false,
        		enableFiltering: true,
        		multiSelect: false,
                enableSelectAll: false,
                enableRowHeaderSelection: false,
        		columnDefs: [
                    {
                        name: 'id',
                        displayName: 'Income ID',
                        type: 'number',
                        sort: {direction: uiGridConstants.ASC, priority: 0}
                    },
//                    {name: 'clientId', displayName: 'Client ID'},
//                    {name: 'name', displayName: 'Client Name'},
//                    {name: 'clientType'},
                    {name: 'date', type: 'date', cellFilter: 'date:\'yyyy-MM-dd\''},
                    {name: 'amount',aggregationType: uiGridConstants.aggregationTypes.sum},
                    {name: 'incomeType'} 
                ],
                data:[]
        };
    	
    	 $scope.showExpenses = function () {
         	$http.get("rest/company/viewExpenses")
             .then(function (response) {
             	$scope.expenses.data = response.data.incomes;
             });
         };
    	
        $scope.ok = function () {
        	$uibModalInstance.close();
        };
        
        $scope.showExpenses();

    };
    app.controller("companyExpenses", companyExpenses);
}());