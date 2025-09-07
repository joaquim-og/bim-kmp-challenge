import SwiftUI
import Shared

struct SakeShopListView: View {
    @StateObject private var viewModel = SakeShopListViewModelWrapper()
    @State private var selectedShop: SakeShop?
    @State private var showingDetail = false
    
    var body: some View {
        NavigationStack {
            ZStack {
                if viewModel.state.isLoading {
                    LoadingView()
                } else if let errorMessage = viewModel.state.errorMessage {
                    ErrorView(
                        message: getErrorMessage(errorMessage),
                        onRetry: { viewModel.fetchSakeShops() }
                    )
                } else if viewModel.state.sakeShopsList.isEmpty {
                    EmptyStateView()
                } else {
                    SakeShopsList(
                        sakeShops: viewModel.state.sakeShopsList,
                        selectedShop: $selectedShop,
                        showingDetail: $showingDetail
                    )
                }
            }
            .navigationTitle("Sake Shops")
            .navigationBarTitleDisplayMode(.large)
            .refreshable {
                viewModel.fetchSakeShops()
            }
            .navigationDestination(isPresented: $showingDetail) {
                if let shop = selectedShop {
                    SakeShopDetailView(sakeShop: shop)
                }
            }
        }
    }
    
    private func getErrorMessage(_ uiText: UiText) -> String {
        switch uiText {
        case let dynamicString as UiTextDynamicString:
            return dynamicString.value
        case let stringResource as UiTextStringResourceId:
            return "An error occurred"
        default:
            return "An error occurred"
        }
    }
}

struct SakeShopsList: View {
    let sakeShops: [SakeShop]
    @Binding var selectedShop: SakeShop?
    @Binding var showingDetail: Bool
    
    var body: some View {
        List(sakeShops, id: \.name) { sakeShop in
            Button(action: {
                selectedShop = sakeShop
                showingDetail = true
            }) {
                SakeShopListItem(sakeShop: sakeShop)
            }
            .buttonStyle(PlainButtonStyle())
            .listRowSeparator(.hidden)
            .listRowInsets(EdgeInsets(top: 6, leading: 16, bottom: 6, trailing: 16))
        }
        .listStyle(PlainListStyle())
    }
}

struct LoadingView: View {
    var body: some View {
        VStack(spacing: 16) {
            ProgressView()
                .scaleEffect(1.2)
            
            Text("Loading sake shops...")
                .font(.body)
                .foregroundColor(.secondary)
        }
    }
}

struct ErrorView: View {
    let message: String
    let onRetry: () -> Void
    
    var body: some View {
        VStack(spacing: 16) {
            Text("Oops! Something went wrong")
                .font(.headline)
                .fontWeight(.bold)
                .foregroundColor(.red)
                .multilineTextAlignment(.center)
            
            Text(message)
                .font(.body)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 32)
            
            Button("Retry") {
                onRetry()
            }
            .buttonStyle(.borderedProminent)
        }
        .padding(32)
    }
}

struct EmptyStateView: View {
    var body: some View {
        VStack(spacing: 16) {
            Text("No sake shops found")
                .font(.headline)
                .fontWeight(.bold)
                .multilineTextAlignment(.center)
            
            Text("Try again later or check your connection")
                .font(.body)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }
        .padding(32)
    }
}

// Preview
#Preview {
    SakeShopListView()
}
