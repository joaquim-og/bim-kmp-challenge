import SwiftUI
import Shared

struct SakeShopListItem: View {
    let sakeShop: SakeShop
    
    var body: some View {
        HStack(spacing: 16) {
            SakeShopImage(
                imageUrl: sakeShop.picture,
                shopName: sakeShop.name,
                height: 80
            )
            .frame(width: 80, height: 80)
            .clipShape(RoundedRectangle(cornerRadius: 12))
            
            VStack(alignment: .leading, spacing: 6) {
                Text(sakeShop.name)
                    .font(.headline)
                    .foregroundColor(.primary)
                    .lineLimit(1)
                
                Text(sakeShop.address)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                    .lineLimit(2)
                
                HStack(spacing: 8) {
                    StarRatingView(rating: sakeShop.rating, starSize: 12)
                    
                    Text(String(format: "%.1f", sakeShop.rating))
                        .font(.caption)
                        .fontWeight(.medium)
                        .foregroundColor(.primary)
                }
            }
            
            Spacer()
        }
        .padding(12)
        .background(Color(.systemBackground))
        .clipShape(RoundedRectangle(cornerRadius: 16))
        .shadow(color: .black.opacity(0.05), radius: 4, x: 0, y: 2)
    }
}
