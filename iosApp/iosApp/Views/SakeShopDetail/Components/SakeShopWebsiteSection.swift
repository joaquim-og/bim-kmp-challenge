import SwiftUI

struct SakeShopWebsiteSection: View {
    let website: String
    let onTap: () -> Void
    
    var body: some View {
        Button(action: onTap) {
            HStack(spacing: 16) {
                Circle()
                    .fill(Color.blue.opacity(0.1))
                    .frame(width: 48, height: 48)
                    .overlay(
                        Image(systemName: "globe")
                            .foregroundColor(.blue)
                            .font(.system(size: 20))
                    )
                
                VStack(alignment: .leading, spacing: 4) {
                    Text("Website")
                        .font(.headline)
                        .foregroundColor(.primary)
                    
                    Text(website)
                        .font(.subheadline)
                        .foregroundColor(.blue)
                        .lineLimit(1)
                }
                
                Spacer()
                
                Image(systemName: "arrow.up.right")
                    .foregroundColor(.blue)
                    .font(.system(size: 14, weight: .medium))
            }
            .padding(16)
            .background(Color(.systemGray6))
            .clipShape(RoundedRectangle(cornerRadius: 16))
        }
        .buttonStyle(PlainButtonStyle())
    }
}
