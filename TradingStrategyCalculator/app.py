import streamlit as st

def main():
    st.set_page_config(page_title="Strategic Trade Calculator", layout="centered")
    
    st.title("🎯 Strategic Trade Calculator")
    st.write("Enter your entry, previous low, and profit target to see your risk profile.")

    # Main Inputs
    col1, col2 = st.columns(2)
    
    with col1:
        st.subheader("Trade Entry")
        buy_price = st.number_input("Your Buy Price ($)", min_value=0.0, step=0.01, format="%.2f")
        profit_target_pct = st.number_input("Desired Profit (%)", min_value=0.0, value=10.0, step=0.5, format="%.1f")

    with col2:
        st.subheader("Stop Loss Basis")
        prev_low = st.number_input("Low of Previous Candle ($)", min_value=0.0, step=0.01, format="%.2f")
        buffer_pct = st.slider("SL Buffer (%)", 0.0, 1.0, 0.1, 0.05, help="Small extra room below the low")

    st.divider()

    if buy_price > 0 and prev_low > 0:
        # 1. Calculate Stop Loss Price
        sl_price = prev_low * (1 - buffer_pct / 100)
        
        # 2. Calculate Take Profit Price
        tp_price = buy_price * (1 + profit_target_pct / 100)
        
        # 3. Calculate Risk & Reward in $
        risk_per_share = buy_price - sl_price
        reward_per_share = tp_price - buy_price
        
        # 4. Calculate % Loss on SL
        sl_loss_pct = (risk_per_share / buy_price) * 100 if buy_price > 0 else 0
        
        # 5. Calculate Risk:Reward Ratio
        rr_ratio = reward_per_share / risk_per_share if risk_per_share > 0 else 0

        # --- RESULTS DISPLAY ---
        
        # Top Row: The Core Answer
        res_col1, res_col2, res_col3 = st.columns(3)
        
        with res_col1:
            st.metric("Stop Loss Price", f"${sl_price:,.2f}")
            st.error(f"Loss on SL: **{sl_loss_pct:.2f}%**")
            
        with res_col2:
            st.metric("Take Profit Price", f"${tp_price:,.2f}")
            st.success(f"Gain on TP: **{profit_target_pct:.1f}%**")
            
        with res_col3:
            st.metric("R:R Ratio", f"1 : {rr_ratio:.2f}")
            if rr_ratio >= 2:
                st.write("✅ **Solid R:R**")
            elif rr_ratio >= 1:
                st.write("⚠️ **Average R:R**")
            else:
                st.write("❌ **Bad R:R**")

        # Visual Risk Bar
        st.subheader("Trade Risk Profile")
        total_range = tp_price - sl_price
        if total_range > 0:
            risk_weight = (risk_per_share / total_range) * 100
            st.progress(int(risk_weight))
            st.caption(f"Risk represents {risk_weight:.1f}% of the total target range.")

        # Summary Table
        st.subheader("Execution Summary")
        summary_data = {
            "Level": ["Entry Price", "Stop Loss (SL)", "Take Profit (TP)", "Risk ($)", "Reward ($)", "Risk (%)", "Reward (%)"],
            "Price/Value": [
                f"${buy_price:,.2f}",
                f"${sl_price:,.2f}",
                f"${tp_price:,.2f}",
                f"${risk_per_share:,.2f}",
                f"${reward_per_share:,.2f}",
                f"{sl_loss_pct:.2f}%",
                f"{profit_target_pct:.1f}%"
            ]
        }
        st.table(summary_data)

    else:
        st.info("Waiting for inputs... Enter your **Buy Price** and **Previous Low** to see the calculation.")

if __name__ == "__main__":
    main()
