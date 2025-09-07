import SwiftUI
import Shared
import Combine

@MainActor
class SakeShopListViewModelWrapper: ObservableObject {
    private let viewModel: SakeShopListViewModel
    @Published var state = SakeShopListState(sakeShopsList: [], isLoading: true, selectedSakeShop: nil, errorMessage: nil)
    
    private var observationTask: Task<Void, Never>?
    
    init() {
        self.viewModel = DIHelper.shared.getSakeShopListViewModel()
        observationTask = observeFlow(viewModel.state) { [weak self] (newState: SakeShopListState) in
            self?.state = newState
        }
    }
    
    func fetchSakeShops() {
        _ = viewModel.fetchSakeShops()
    }
    
    deinit {
        observationTask?.cancel()
    }
}
