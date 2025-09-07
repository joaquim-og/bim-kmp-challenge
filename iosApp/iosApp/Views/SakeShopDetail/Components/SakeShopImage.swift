import SwiftUI

struct SakeShopImage: View {
    let imageUrl: String?
    let shopName: String
    let height: CGFloat
    
    init(imageUrl: String?, shopName: String, height: CGFloat = 280) {
        self.imageUrl = imageUrl
        self.shopName = shopName
        self.height = height
    }
    
    var body: some View {
        ZStack {
            if let imageUrl = imageUrl, let url = URL(string: imageUrl) {
                AsyncImage(url: url) { phase in
                    switch phase {
                    case .success(let image):
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                    case .failure(_), .empty:
                        placeholderView
                    @unknown default:
                        placeholderView
                    }
                }
            } else {
                placeholderView
            }
        }
        .frame(height: height)
        .clipped()
        .background(Color(.systemGray6))
    }
    
    private var placeholderView: some View {
        ZStack {
            Color(.systemGray5)
            VStack(spacing: 8) {
                Image(systemName: "photo")
                    .font(.system(size: 40))
                    .foregroundColor(.gray)
                Text("No Image")
                    .font(.caption)
                    .foregroundColor(.gray)
            }
        }
    }
}
